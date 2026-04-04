package com.fightkeeper.service;

import com.fightkeeper.model.FightResult;
import com.fightkeeper.repository.FightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FightResultService {

    private final FightRepository repo;

    public FightResultService(FightRepository repo) {
        this.repo = repo;
    }


    public void saveFightResult(String player1, String player2, String howBadWasit, String reasonForLoss, String secondReasonForLoss, String fightTime, String comment) {
        FightResult result = new FightResult(UUID.randomUUID().toString(), player1, player2, reasonForLoss, secondReasonForLoss, fightTime, howBadWasit, comment);
        repo.save(result);
    }

    public void saveFightResult(FightResult fight) {
        this.saveFightResult(fight.getFighter1(), fight.getFighter2(),fight.getHowBadWasIt(), fight.getReasonForLoss(),fight.getSecondReasonForLoss(),fight.getFightTime(), fight.getComment());
    }

    public List<FightResult> getAllResults() {
            return repo.getAllResults();
        }
}