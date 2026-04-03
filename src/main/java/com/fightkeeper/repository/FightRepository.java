package com.fightkeeper.repository;

import com.fightkeeper.model.fightKeeperDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FightRepository {


    private final DynamoDbTable<fightKeeperDB> table;

    private final String TABLE_NAME = "FightKeeperDB";

    public FightRepository(DynamoDbEnhancedClient enhancedClient) {
        System.out.println("scoobs 1");
        this.table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(fightKeeperDB.class));
    }

    public void save(fightKeeperDB fightKeeperDB) {
        table.putItem(fightKeeperDB);
    }

    public List<fightKeeperDB> getAllResults() {
        List<fightKeeperDB> resultList = new ArrayList<>();
        table.scan().items().forEach(resultList::add);
        return resultList;
    }

}
