package com.example.blogpostapi.service;

public interface SQSConfigService {

    public String createQueue();
    String sendMessage(String message);
}
