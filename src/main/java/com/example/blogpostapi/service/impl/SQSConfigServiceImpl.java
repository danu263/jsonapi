package com.example.blogpostapi.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.example.blogpostapi.configuration.PropertiesConfiguration;
import com.example.blogpostapi.service.SQSConfigService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SQSConfigServiceImpl implements SQSConfigService {

//    private static final Logger LOG = LoggerFactory.getLogger(SQSConfigServiceImpl.class);

    @Autowired
    PropertiesConfiguration configuration;

    private static final String QUEUE_NAME = "my_first_queue";

    private AmazonSQS getSQS() {
        AWSCredentials credentials         = new BasicAWSCredentials(configuration.getApiKey(), configuration.getSecretKey());
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);

        return AmazonSQSClientBuilder.standard().withCredentials(credentialsProvider).withRegion(Regions.SA_EAST_1).build();
    }

    @Override
    public String createQueue() {
        AmazonSQS sqs = getSQS();
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(QUEUE_NAME)
                .addAttributesEntry("DelaySeconds", "60")
                .addAttributesEntry("MessageRetentionPeriod", "86400");

        CreateQueueResult result = null;
        try {
            result = sqs.createQueue(createQueueRequest);
        } catch (AmazonSQSException e) {
            log.error("[SQS SERVICE] | error sending message", e);
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
        if (result != null) {
            log.info("[SQS SERVICE] | queue created successfully {}", result.getQueueUrl());
            return result.getQueueUrl();
        }
        return null;
    }

    @Override
    public String sendMessage(String message) {
        AmazonSQS sqs = getSQS();
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl("https://sqs.sa-east-1.amazonaws.com/657492150355/my_first_queue")
                .withMessageBody(message);
//                .withDelaySeconds(5);
        SendMessageResult response = null;
        try {
            response = sqs.sendMessage(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        if (response != null) {
            log.info("[SQS SERVICE] | message sent successfully {}", response.getMessageId());
            return response.getMessageId();
        }
        return null;
    }
}
