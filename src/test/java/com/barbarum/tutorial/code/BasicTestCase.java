package com.barbarum.tutorial.code;

import com.barbarum.common.testframework.FunctionUtil;
import com.barbarum.tutorial.util.AssertUtil;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;


public abstract class BasicTestCase extends TestCase {

    private final FunctionUtil util = FunctionUtil.get(FunctionUtil.Type.JUNIT);
    protected List<Object[]> dataset = new ArrayList<>();


    public FunctionUtil getUtil() {
        return util;
    }

    protected <E, A, B> void testTemplate(E expected, A param1, B param2, BiFunction<A, B, E> function) {
        this.getUtil().testEquals(expected, param1, param2, Stream.of(function));
    }

    protected void addTestData(String testDescription, String sample, String expected) {
        dataset.add(AssertUtil.generateTestData("3 1", "1 3"));
    }

}
