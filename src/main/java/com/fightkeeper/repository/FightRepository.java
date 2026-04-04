package com.fightkeeper.repository;

import com.fightkeeper.model.FightResult;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

//TODO maybe make this a JPA repo
@Repository
public class FightRepository {


    private final DynamoDbTable<FightResult> table;

    private final String TABLE_NAME = "fightKeeperDB";

    public FightRepository(DynamoDbEnhancedClient enhancedClient) {
        this.table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(FightResult.class));
    }

    public void save(FightResult fightKeeperDB) {
        table.putItem(fightKeeperDB);
    }

    public List<FightResult> getAllResults() {
        List<FightResult> resultList = new ArrayList<>();
        table.scan().items().forEach(resultList::add);
        return resultList;
    }

}
