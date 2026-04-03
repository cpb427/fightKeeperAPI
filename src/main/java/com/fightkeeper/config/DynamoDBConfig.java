package com.fightkeeper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.dynamodb.accessKey}")
    private String DBAccessKey;

    @Value("${aws.dynamodb.secretKey}")
    private String DBSecret;

    @Bean
    public DynamoDbClient dynamoDbClient() {

        System.out.println("scoobs");
        System.out.println(DBAccessKey);
        
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                DBAccessKey,
                DBSecret
        );

        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient client) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }
}