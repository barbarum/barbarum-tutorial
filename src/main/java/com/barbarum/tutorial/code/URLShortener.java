package com.barbarum.tutorial.code;

import com.google.common.hash.BloomFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class URLShortener {

    private Map<Long, String> urlMapping = new HashMap<>();
    private AtomicLong sequence = new AtomicLong(0);

    private static String BASE_TABLE = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
    private static char STARTER = BASE_TABLE.charAt(0);

    private static int LENGTH = 6;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        long id = sequence.getAndIncrement();
        urlMapping.put(id, longUrl);
        return format(encodeToBase62(id), LENGTH);
    }

    private String encodeToBase62(long id) {
        if (id == 0) {
            return String.valueOf(STARTER);
        }
        StringBuilder builder = new StringBuilder();
        long temp = id;
        while (temp > 0) {
            builder.append(BASE_TABLE.charAt((int) (temp % BASE_TABLE.length())));
            temp = temp / BASE_TABLE.length();
        }
        return builder.reverse().toString();
    }

    private String format(String url, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = url.length(); i < length; i++) {
            builder.append(STARTER);
        }
        return builder.append(url).toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        long id = decodeFromBase62(shortUrl);
        if (id == -1) {
            return null;
        }
        return urlMapping.getOrDefault(id, null);
    }

    private long decodeFromBase62(String shortUrl) {
        if (shortUrl == null || shortUrl.isEmpty() || shortUrl.length() > 6) {
            return -1;
        }
        long id = 0;
        for (char item : shortUrl.toCharArray()) {
            int index = BASE_TABLE.indexOf(String.valueOf(item));
            if (index == -1) {
                return -1;
            }
            id = id * BASE_TABLE.length() + index;
        }
        return id;
    }

    public static void main(String args[]) {
        URLShortener instance = new URLShortener();

        String shortURL = null;
        for (int i = 0; i < 100; i++) {
            shortURL = instance.encode("http://www.google.com/");
            System.out.println(shortURL);
        }

        System.out.println(instance.decode(shortURL));

        System.out.println(instance.decode("aadf8"));

        System.out.println(new Double(Math.pow(62, 6)).longValue());
        System.out.println(((long) (new Double(Math.pow(10, 9)).longValue() * 3 * 9.6)) >> (3 + 20));
        System.out.println(new Double(Math.pow(10, 9)).longValue() * 3 * 8 * 2 >> (20));

        System.out.println(Integer.MAX_VALUE);


    }
}
