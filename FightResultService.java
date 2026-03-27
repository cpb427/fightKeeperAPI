package com.example.fightkeeperapi.service;

import com.example.fightkeeperapi.model.FightResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Service
public class FightResultService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "fight-results";

    public FightResult saveFight(FightResult fightResult) {
        String id = UUID.randomUUID().toString();
        fightResult.setId(id);

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(id).build());
        item.put("game", AttributeValue.builder().s(fightResult.getGame()).build());
        item.put("opponent", AttributeValue.builder().s(fightResult.getOpponent()).build());
        item.put("result", AttributeValue.builder().s(fightResult.getResult()).build());
        item.put("character", AttributeValue.builder().s(fightResult.getCharacter()).build());
        item.put("opponentCharacter", AttributeValue.builder().s(fightResult.getOpponentCharacter()).build());
        item.put("notes", AttributeValue.builder().s(fightResult.getNotes() != null ? fightResult.getNotes() : "").build());
        item.put("timestamp", AttributeValue.builder().s(fightResult.getTimestamp().toString()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
        return fightResult;
    }

    public FightResult getFightById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);
        return mapItemToFight(response.item());
    }

    public List<FightResult> getAllFights() {
        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);
        List<FightResult> fights = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {
            fights.add(mapItemToFight(item));
        }

        return fights;
    }

    public FightResult updateFight(FightResult fightResult) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(fightResult.getId()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        updatedValues.put("game", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(fightResult.getGame()).build())
                .action(AttributeAction.PUT).build());
        updatedValues.put("opponent", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(fightResult.getOpponent()).build())
                .action(AttributeAction.PUT).build());
        updatedValues.put("result", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(fightResult.getResult()).build())
                .action(AttributeAction.PUT).build());
        updatedValues.put("character", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(fightResult.getCharacter()).build())
                .action(AttributeAction.PUT).build());
        updatedValues.put("opponentCharacter", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(fightResult.getOpponentCharacter()).build())
                .action(AttributeAction.PUT).build());
        updatedValues.put("notes", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(fightResult.getNotes() != null ? fightResult.getNotes() : "").build())
                .action(AttributeAction.PUT).build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .attributeUpdates(updatedValues)
                .build();

        dynamoDbClient.updateItem(request);
        return fightResult;
    }

    public void deleteFight(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        dynamoDbClient.deleteItem(request);
    }

    public List<FightResult> getFightsByGame(String game) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(" :game", AttributeValue.builder().s(game).build());

        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .filterExpression("game = :game")
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);
        List<FightResult> fights = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {
            fights.add(mapItemToFight(item));
        }

        return fights;
    }

    private FightResult mapItemToFight(Map<String, AttributeValue> item) {
        if (item == null || item.isEmpty()) {
            return null;
        }

        FightResult fight = new FightResult();
        fight.setId(item.get("id") != null ? item.get("id").s() : "");
        fight.setGame(item.get("game") != null ? item.get("game").s() : "");
        fight.setOpponent(item.get("opponent") != null ? item.get("opponent").s() : "");
        fight.setResult(item.get("result") != null ? item.get("result").s() : "");
        fight.setCharacter(item.get("character") != null ? item.get("character").s() : "");
        fight.setOpponentCharacter(item.get("opponentCharacter") != null ? item.get("opponentCharacter").s() : "");
        fight.setNotes(item.get("notes") != null ? item.get("notes").s() : "");

        return fight;
    }
}