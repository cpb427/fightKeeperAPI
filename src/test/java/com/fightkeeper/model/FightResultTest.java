package com.fightkeeper.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fightkeeper.model.FightResult;

public class FightResultTest {

    private FightResult fightResult;

    @BeforeEach
    public void setUp() {
        fightResult = new FightResult(); // Using default constructor
    }

    @Test
    public void testConstructorAndGetters() {
        FightResult result = new FightResult("1", "FighterA", "FighterB", "combo", "spacing", "01-01-2026","it was close", "comment");
        Assertions.assertEquals("1", result.getFkdb1());
        Assertions.assertEquals("FighterA", result.getFighter1());
        Assertions.assertEquals("FighterB", result.getFighter2());
    }


}