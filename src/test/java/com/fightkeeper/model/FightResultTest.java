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
        FightResult result = new FightResult(1, "FighterA", "FighterB", true);
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("FighterA", result.getWinner());
        Assertions.assertEquals("FighterB", result.getLoser());
        Assertions.assertTrue(result.isVictory());
    }

    @Test
    public void testSettersAndGetters() {
        fightResult.setId(1);
        fightResult.setWinner("FighterA");
        fightResult.setLoser("FighterB");
        fightResult.setVictory(true);

        Assertions.assertEquals(1, fightResult.getId());
        Assertions.assertEquals("FighterA", fightResult.getWinner());
        Assertions.assertEquals("FighterB", fightResult.getLoser());
        Assertions.assertTrue(fightResult.isVictory());
    }

    @Test
    public void testFieldValidation() {
        fightResult.setId(1);
        fightResult.setWinner("FighterA");
        fightResult.setLoser("FighterB");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fightResult.setWinner(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fightResult.setLoser(null);
        });
    }
}