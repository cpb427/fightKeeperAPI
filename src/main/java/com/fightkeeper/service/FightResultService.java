package com.fightkeeper.service;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DynamoDBException;

public class FightResultService {
    
    private final DynamoDB dynamoDB;
    private final String tableName = "FightResults";
    
    public FightResultService(AmazonDynamoDB client) {
        this.dynamoDB = new DynamoDB(client);
        }
        
    public void saveFightResult(String fightId, String winner, String loser, int score) {
        Table table = dynamoDB.getTable(tableName);
        Item item = new Item()
                        .withPrimaryKey("FightID", fightId)
                        .withString("Winner", winner)
                        .withString("Loser", loser)
                        .withNumber("Score", score);
        try {
            table.putItem(item);
            } catch (DynamoDBException e) {
                System.err.println("Unable to add fight result: " + e.getMessage());
            }
    }
                // Additional methods for retrieving/updating results can be added here\n
}