package com.example.blogpostapi.utils;

public final class HatchwaysHost {

    public static final String URL = "https://api.hatchways.io";

    public enum Blog {
        POSTS("/assessment/blog/posts");

        private final String value;

        Blog(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
