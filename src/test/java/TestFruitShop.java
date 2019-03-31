import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFruitShop {
    private FruitShop fruitShop;

    @Before
    public void setUp() {
        fruitShop = new FruitShop();
        fruitShop.addFruits("src/main/resources/FirstDelivery.json");
        fruitShop.addFruits("src/main/resources/SecondDelivery.json");
        fruitShop.save("src/main/resources/AllFruitData.json");
        fruitShop.load("src/main/resources/AllFruitData.json");
        fruitShop.setNumOfDelivery(0);
    }

    @Test
    public void testGetAvailableFruits() {
        int numOfAvailableFruits = fruitShop.getAvailableFruits("24-05-2012").size();
        assertEquals((numOfAvailableFruits), 16);
    }

    @Test
    public void testGetSpoiledFruits() {
        int numOfSpoiledFruits = fruitShop.getSpoiledFruits("24-05-2012").size();
        assertEquals((numOfSpoiledFruits), 2);

    }

    @Test
    public void testGetSpoiledAndAvailableFruitsAdvanced() {
        int numOfSpoiledFruits = fruitShop.getSpoiledFruits("24-05-2012", TypeOfFruit.KIWI).size();
        int numOfAvailableFruits = fruitShop.getAvailableFruits("24-05-2012", TypeOfFruit.KIWI).size();
        assertEquals((numOfSpoiledFruits + numOfAvailableFruits), 3);
    }

    @Test
    public void testGetAvailableFruitsAdvanced() {
        int numOfAvailableFruits = fruitShop.getAvailableFruits("24-05-2012", TypeOfFruit.KIWI).size();
        assertEquals((numOfAvailableFruits), 2);
    }

    @Test
    public void testGetSpoiledFruitsAdvanced() {
        int numOfSpoiledFruits = fruitShop.getSpoiledFruits("24-05-2012", TypeOfFruit.KIWI).size();
        assertEquals((numOfSpoiledFruits), 1);

    }

    @Test
    public void testGetAddedFruits() {
        int numOfFruits = fruitShop.getAddedFruits("18-05-2012").size();
        assertEquals((numOfFruits), 3);
    }

    @Test
    public void testGetAddedFruitsAdvanced() {
        int numOfFruits = fruitShop.getAddedFruits("23-05-2012", TypeOfFruit.KIWI).size();
        assertEquals((numOfFruits), 1);
    }
}
