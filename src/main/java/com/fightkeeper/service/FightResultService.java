package com.fightkeeper.service;

import com.fightkeeper.model.fightKeeperDB;
import com.fightkeeper.repository.FightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class FightResultService {

    private final FightRepository repo;

    public FightResultService(FightRepository repo) {
        this.repo = repo;
    }


    public void saveFightResult(String player1, String player2, String howBadWasit, String reasonForLoss, String secondReasonForLoss, String comment) {
        fightKeeperDB result = new fightKeeperDB(UUID.randomUUID().toString(), player1, player2, reasonForLoss, secondReasonForLoss, "", howBadWasit, comment);
        repo.save(result);
    }

    public void saveFightResult(fightKeeperDB fight) {
        this.saveFightResult(fight.getFighter1(), fight.getFighter2(),fight.getHowBadWasIt(), fight.getReasonForLoss(),fight.getSecondReasonForLoss(), fight.getComment());
    }
    // Additional methods for retrieving/updating results can be added here\n
    public List<fightKeeperDB> getAllResults() {
            return repo.getAllResults();
        }
}