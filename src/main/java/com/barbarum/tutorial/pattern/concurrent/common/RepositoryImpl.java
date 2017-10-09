package com.barbarum.tutorial.pattern.concurrent.common;

import java.io.UnsupportedEncodingException;

public class RepositoryImpl implements Repository {

    @Override
    public String process(byte[] data) {
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
