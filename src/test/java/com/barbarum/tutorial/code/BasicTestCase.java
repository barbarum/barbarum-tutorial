package com.barbarum.tutorial.code;

import com.barbarum.common.testframework.FunctionUtil;

public class BasicTestCase {

    private final FunctionUtil util = FunctionUtil.get(FunctionUtil.Type.JUNIT);


    public FunctionUtil getUtil() {
        return util;
    }
}
