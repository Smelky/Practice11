import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class TestFruitShop {

    @Test
    public void testFruitShop() throws IOException, ParseException {
        FruitShop fruitShop = new FruitShop();
        fruitShop.addFruits("src/main/resources/FirstDelivery.json");
        fruitShop.save("src/main/resources/AllFruitData.json");
        fruitShop.addFruits("src/main/resources/SecondDelivery.json");
        fruitShop.load("src/main/resources/AllFruitData.json");
        fruitShop.getSpoiledFruits("24-05-2012");
        fruitShop.getAvailableFruits("24-05-2012");
        fruitShop.getSpoiledFruits("24-05-2012", TypeOfFruit.KIWI);
        fruitShop.getAvailableFruits("24-05-2012", TypeOfFruit.KIWI);
        fruitShop.getAddedFruits("18-05-2012");
        fruitShop.getAddedFruits("23-05-2012", TypeOfFruit.KIWI);
    }
}
