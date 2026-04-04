package com.fightkeeper.config;

import com.fightkeeper.util.SecretsUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.access-key}")
    private String accessKey;

    @Value("${aws.secret-key}")
    private String secretKey;

//    @Bean
//    public AwsCredentialsProvider awsCredentialsProvider() {
//        return DefaultCredentialsProvider.create();
//    }

    @Bean
    public DynamoDbClient dynamoDbClient() {

//System.out.println("scoobs b");
//        String access = SecretsUtil.getAccessKey();
//        String secret = SecretsUtil.getAccessSecret();
//        System.out.println("Scoobs A");
//        System.out.println(secret);
//        System.out.println(access);
//        AwsBasicCredentials credentials = AwsBasicCredentials.create(
//                SecretsUtil.getAccessKey(),
//                SecretsUtil.getAccessSecret()
//        );
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);


        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient client) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }
}