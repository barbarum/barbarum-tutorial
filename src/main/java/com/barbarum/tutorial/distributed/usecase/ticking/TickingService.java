package com.barbarum.tutorial.distributed.usecase.ticking;


import com.barbarum.tutorial.distributed.usecase.ticking.policy.SequenceOverflowPolicy;
import com.barbarum.tutorial.distributed.usecase.ticking.policy.SystemClockBackoffPolicy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Ticking service implementation
 * | 1 bit: Reversed Sign Bit | 41 bits: Timestamp | 5 bits: Cluster ID | 1 bit: reversed bit for expansion | 5 bits: Work ID | 11 bits: auto increase sequence in a millisecond
 */
public class TickingService implements Tick {

    private final TickConfig config;

    private SystemClockBackoffPolicy systemClockBackoffPolicy;
    private SequenceOverflowPolicy sequenceOverflowPolicy = new SequenceOverflowPolicy() {
        @Override
        public void handle() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    // Base epoch datetime: Sunday, 1 January 2017 00:00:00 GMT
    private final long baseEpochTimestamp = 1483228800000L;

    private final AtomicLong lastTimestamp;
    private final long clusterId;
    private final long workIdReserved = 0;
    private final long workId;
    private long sequence;

    private final byte sequenceBits = 11;
    private final byte workIdBits = 5;
    private final byte workIdReversedBits = 1;
    private final byte clusterIdBits = 5;

    private final long maxClusterId = ~(-1 << clusterIdBits);
    private final long maxWorkId = ~(-1 << workIdBits);

    private final byte workIdShift = sequenceBits;
    private final byte workIdReversedShift = workIdShift + workIdBits;
    private final byte clusterIdShift = workIdReversedShift + workIdReversedBits;
    private final byte timestampShift = clusterIdShift + clusterIdBits;

    public TickingService(TickConfig config) throws OverflowException {
        this.config = config;

        if (config.getClusterId() > maxClusterId) {
            throw new OverflowException();
        }
        if (config.getWorkId() > maxWorkId) {
            throw new OverflowException();
        }

        this.clusterId = config.getClusterId();
        this.workId = config.getWorkId();

        this.lastTimestamp = new AtomicLong(config.getLastTimestamp());
    }

    @Override
    public synchronized long next() {
        long currentTimestamp = getCurrentTimestamp();

        if (currentTimestamp < this.getLastTimestamp()) {
            this.systemClockBackoffPolicy.handle(this.getLastTimestamp() - currentTimestamp);
            return next();
        }
        if (currentTimestamp > this.getLastTimestamp()) {
            this.lastTimestamp.set(currentTimestamp);
            sequence = 0;
        }
        if ((sequence >> 10) > 0) {
            this.sequenceOverflowPolicy.handle();
            return next();
        }

        long id = getLastTimestamp() << timestampShift;
        id |= (clusterId << clusterIdShift);
        id |= (workIdReserved << workIdReversedShift);
        id |= (workId << workIdShift);
        id |= sequence;

        sequence++;

        return id;
    }

    private long getCurrentTimestamp() {
        return System.currentTimeMillis() - baseEpochTimestamp;
    }

    /**
     * Get last used timestamp.
     *
     * @return last used timestamp.
     */
    public long getLastTimestamp() {
        return lastTimestamp.get();
    }

    @Override
    public TickConfig getTickConfig() {
        return this.config;
    }

    public void setSystemClockBackoffPolicy(SystemClockBackoffPolicy systemClockBackoffPolicy) {
        this.systemClockBackoffPolicy = systemClockBackoffPolicy;
    }

    public SystemClockBackoffPolicy getSystemClockBackoffPolicy() {
        return systemClockBackoffPolicy;
    }


    public SequenceOverflowPolicy getSequenceOverflowPolicy() {
        return sequenceOverflowPolicy;
    }

    public void setSequenceOverflowPolicy(SequenceOverflowPolicy sequenceOverflowPolicy) {
        this.sequenceOverflowPolicy = sequenceOverflowPolicy;
    }
}
