package com.fightkeeper.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class FightResult {

    private String fkdb1;

    private String fighter1;

    private String fighter2;

    private String ReasonForLoss;

    private String secondReasonForLoss;

    private String fightTime;

    private String howBadWasIt;

    private String comment;

    @DynamoDbPartitionKey
    public String getFkdb1() {
        return fkdb1;
    }

}