package com.fightkeeper.repository;

import com.fightkeeper.model.FightResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

public class FightRepositoryTest {

    @Mock
    private DynamoDbTable<FightResult> dynamoDBMapper;

    @InjectMocks
    private FightRepository fightRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveFight() {
        FightResult fight = new FightResult();
        // set fight properties here

        fightRepository.save(fight);
        verify(dynamoDBMapper, times(1)).putItem(fight);
    }

    //todo: this later
//    @Test
//    public void testGetAllResults() {
//        List<FightResult> mockFights = Collections.singletonList(new FightResult()); // mock list of fights
//        when(dynamoDBMapper.scan().items()).thenReturn(mockFights);
//
//        List<FightResult> results = fightRepository.getAllResults();
//        assertEquals(1, results.size());  // assuming we expect one result
//        verify(dynamoDBMapper, times(1)).scan();
//    }
}