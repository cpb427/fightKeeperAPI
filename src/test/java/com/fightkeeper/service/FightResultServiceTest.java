package com.fightkeeper.service;

import com.fightkeeper.model.FightResult;
import com.fightkeeper.repository.FightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FightResultService Tests")
class FightResultServiceTest {

    @Mock
    private FightRepository fightRepository;

    @InjectMocks
    private FightResultService fightResultService;

    private FightResult testFightResult;

    @BeforeEach
    void setUp() {
        testFightResult = new FightResult(
                "fight-123",
                "Player1",
                "Player2",
                "Bad spacing",
                "Missed combo",
                "2026-04-04T10:30:00Z",
                "Extremely bad",
                "Got destroyed"
        );
    }

    @Test
    @DisplayName("Should save fight result with all parameters")
    void testSaveFightResult_WithAllParameters() {
        String player1 = "Player1";
        String player2 = "Player2";
        String howBadWasIt = "Very bad";
        String reasonForLoss = "Poor defense";
        String secondReason = "Missed punish";
        String fightTime = "2026-04-04T10:30:00Z";
        String comment = "Need more practice";

        fightResultService.saveFightResult(player1, player2, howBadWasIt, reasonForLoss, secondReason, fightTime, comment);

        ArgumentCaptor<FightResult> argumentCaptor = ArgumentCaptor.forClass(FightResult.class);
        verify(fightRepository, times(1)).save(argumentCaptor.capture());

        FightResult capturedResult = argumentCaptor.getValue();
        assertThat(capturedResult.getFighter1()).isEqualTo(player1);
        assertThat(capturedResult.getFighter2()).isEqualTo(player2);
    }

    @Test
    @DisplayName("Should retrieve all fight results")
    void testGetAllResults_Success() {
        List<FightResult> mockResults = new ArrayList<>();
        mockResults.add(testFightResult);
        when(fightRepository.getAllResults()).thenReturn(mockResults);

        List<FightResult> results = fightResultService.getAllResults();

        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getFighter1()).isEqualTo("Player1");
        verify(fightRepository, times(1)).getAllResults();
    }
}