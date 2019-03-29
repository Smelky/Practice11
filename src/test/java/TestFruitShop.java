import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFruitShop {

    @Test
    public void testFruitShop() {
        FruitShop fruitShop = new FruitShop();
        fruitShop.addFruits("src/main/resources/FirstDelivery.json");
        fruitShop.save("src/main/resources/AllFruitData.json");
        fruitShop.addFruits("src/main/resources/SecondDelivery.json");
        fruitShop.load("src/main/resources/AllFruitData.json");
        int numOfSpoiledFruits = fruitShop.getSpoiledFruits("24-05-2012").size();
        int numOfAvailableFruits = fruitShop.getAvailableFruits("24-05-2012").size();
        fruitShop.getSpoiledFruits("24-05-2012", TypeOfFruit.KIWI);
        fruitShop.getAvailableFruits("24-05-2012", TypeOfFruit.KIWI);
        fruitShop.getAddedFruits("18-05-2012");
        fruitShop.getAddedFruits("23-05-2012", TypeOfFruit.KIWI);
        assertEquals((numOfSpoiledFruits + numOfAvailableFruits), 18);
    }
}
