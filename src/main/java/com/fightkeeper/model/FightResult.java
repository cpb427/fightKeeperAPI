package com.fightkeeper.model;

public class FightResult {
    private String fighter1;
    private String fighter2;
    private String winner;
    private String fightTime;

    public FightResult(String fighter1, String fighter2, String winner, String fightTime) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.winner = winner;
        this.fightTime = fightTime;
    }

    public String getFighter1() {
        return fighter1;
    }

    public String getFighter2() {
        return fighter2;
    }

    public String getWinner() {
        return winner;
    }

    public String getFightTime() {
        return fightTime;
    }
}