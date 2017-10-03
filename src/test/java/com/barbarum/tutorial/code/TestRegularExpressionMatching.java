package com.barbarum.tutorial.code;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public class TestRegularExpressionMatching extends BasicTestCase {

    @Test
    public void testIsMatch() throws Exception {
        testTemplate(false, "aa", "a", RegularExpressionMatching::isMatch);
        testTemplate(true, "aa", "aa", RegularExpressionMatching::isMatch);
        testTemplate(false, "aaa", "aa", RegularExpressionMatching::isMatch);
        testTemplate(true, "aa", "a*", RegularExpressionMatching::isMatch);
        testTemplate(true, "aa", ".*", RegularExpressionMatching::isMatch);
        testTemplate(true, "ab", ".*", RegularExpressionMatching::isMatch);
        testTemplate(true, "aab", "c*a*b", RegularExpressionMatching::isMatch);
        testTemplate(true, "a", "ab*", RegularExpressionMatching::isMatch);
    }


    private void testTemplate(boolean expected, String param1, String param2, BiFunction<String, String, Boolean> function) {
        this.getUtil().testEquals(expected, param1, param2, Stream.of(function));
    }
}
