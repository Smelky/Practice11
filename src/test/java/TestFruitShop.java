import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFruitShop {
    FruitShop fruitShop;

    @Before
    public void setUp(){
        fruitShop = new FruitShop();
        fruitShop.addFruits("src/main/resources/FirstDelivery.json");
        fruitShop.save("src/main/resources/AllFruitData.json");
        fruitShop.addFruits("src/main/resources/SecondDelivery.json");
        fruitShop.load("src/main/resources/AllFruitData.json");
    }

    @Test
    public void testGetSpoiledAndAvailableFruits(){
        int numOfSpoiledFruits = fruitShop.getSpoiledFruits("24-05-2012").size();
        int numOfAvailableFruits = fruitShop.getAvailableFruits("24-05-2012").size();
        assertEquals((numOfSpoiledFruits + numOfAvailableFruits), 14);
    }

    @Test
    public void testGetSpoiledAndAvailableFruitsAdvanced(){
        int numOfSpoiledFruits =  fruitShop.getSpoiledFruits("24-05-2012", TypeOfFruit.KIWI).size();
        int numOfAvailableFruits = fruitShop.getAvailableFruits("24-05-2012", TypeOfFruit.KIWI).size();
        assertEquals((numOfSpoiledFruits + numOfAvailableFruits), 3);
    }

    @Test
    public void testGetAddedFruits(){
        int numOfFruits = fruitShop.getAddedFruits("18-05-2012").size();
        assertEquals((numOfFruits), 3);
    }

    @Test
    public void testGetAddedFruitsAdvanced(){
        int numOfFruits = fruitShop.getAddedFruits("23-05-2012", TypeOfFruit.KIWI).size();
        assertEquals((numOfFruits), 1);
    }
}
