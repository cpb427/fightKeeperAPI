package com.fightkeeper.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

public class FightRepositoryTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private FightRepository fightRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveFight() {
        Fight fight = new Fight();
        // set fight properties here

        fightRepository.save(fight);
        verify(dynamoDBMapper, times(1)).save(fight);
    }

    @Test
    public void testGetAllResults() {
        List<Fight> mockFights = Collections.singletonList(new Fight()); // mock list of fights
        when(dynamoDBMapper.scan(Fight.class, null)).thenReturn(mockFights);

        List<Fight> results = fightRepository.getAllResults();
        assertEquals(1, results.size());  // assuming we expect one result
        verify(dynamoDBMapper, times(1)).scan(Fight.class, null);
    }
}