package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Dict;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestDict {

    @Test
    public void testDelete() {
        Dict dict = new Dict();
        dict.insert("abb").insert("abc").insert("word").insert("xy").insert("xyz").insert("xyzb");

        dict.delete("love");
        dict.delete("word");
        dict.delete("xyz");
        dict.delete("abc");
    }

    @Test
    public void testPatternMatch() {
        List<Integer> result = PatternMatch.search("banana", "ana");
        Collections.sort(result);
        Assert.assertEquals(Arrays.asList(1, 3), result);
    }

    @Test
    public void testLongestPrefix(){
        Dict dict = new Dict();
        dict.insert("word").insert("cat").insert("cam").insert("name");

        Assert.assertEquals("cam", dict.findLongPrefixKey("camera"));
        Assert.assertEquals("cat", dict.findLongPrefixKey("cataract"));
    }
}
