package com.example.blogpostapi.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PropertiesConfiguration {

    @Value("${aws.api.key}")
    private String apiKey;
    @Value("${aws.secret.key}")
    private String secretKey;
}
