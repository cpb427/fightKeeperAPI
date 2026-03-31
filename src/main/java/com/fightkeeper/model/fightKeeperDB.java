package com.fightkeeper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class fightKeeperDB {

    private String fkdb1;

    private String fighter1;

    private String fighter2;

    private String ReasonForLoss;

    private String secondReasonForLoss;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date fightTime;

    private String howBadWasIt;

    private String comment;

    @DynamoDbPartitionKey
    public String getFkdb1() {
        return fkdb1;
    }

}