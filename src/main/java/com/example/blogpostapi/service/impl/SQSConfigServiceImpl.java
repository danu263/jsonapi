package com.example.blogpostapi.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.example.blogpostapi.configuration.PropertiesConfiguration;
import com.example.blogpostapi.service.SQSConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQSConfigServiceImpl implements SQSConfigService {

    @Autowired
    PropertiesConfiguration configuration;

    private static final String QUEUE_NAME = "my_queue@sqs";

    @Override
    public String createQueue() {
        AWSCredentials credentials         = new BasicAWSCredentials(configuration.getApiKey(), configuration.getSecretKey());
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);

        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(credentialsProvider).build();
        CreateQueueRequest create_request = new CreateQueueRequest(QUEUE_NAME)
                .addAttributesEntry("DelaySeconds", "60")
                .addAttributesEntry("MessageRetentionPeriod", "86400");

        CreateQueueResult result = null;
        try {
            result = sqs.createQueue(create_request);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
        if (result != null) {
            return result.getQueueUrl();
        }
        return null;
    }
}
