package com.example.blogpostapi.utils;

public final class HatchwaysEnums {

    public enum SortBy {
        ID("id"),
        READS("reads"),
        LIKES("likes"),
        POPULARITY("popularity");

        private final String value;

        SortBy(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Order {
        ASC("asc"),
        DESC("desc");

        private final String value;

        Order(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
