package com.barbarum.tutorial.code;

import com.barbarum.common.testframework.FunctionUtil;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public class BasicTestCase {

    private final FunctionUtil util = FunctionUtil.get(FunctionUtil.Type.JUNIT);


    public FunctionUtil getUtil() {
        return util;
    }

    protected <E, A, B> void testTemplate(E expected, A param1, B param2, BiFunction<A, B, E> function) {
        this.getUtil().testEquals(expected, param1, param2, Stream.of(function));
    }
}
