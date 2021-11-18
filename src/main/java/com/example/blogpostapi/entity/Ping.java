package com.example.blogpostapi.entity;

import java.util.Objects;

public class Ping {

    private Boolean success;

    public Ping(Boolean success) {
        this.success = success;
    }

    public Ping() {
    }

    @Override
    public String toString() {
        return "Ping{" +
                "success=" + success +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ping ping = (Ping) o;
        return Objects.equals(success, ping.success);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
