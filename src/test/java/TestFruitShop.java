import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class TestFruitShop {

    @Test
    public void testFruitShop() throws IOException, ParseException {
        FruitShop fruitShop = new FruitShop();
        fruitShop.addFruits("src/main/resources/FirstDelivery.json");
        fruitShop.save("src/main/resources/AllFruitData.json");
        fruitShop.load("src/main/resources/AllFruitData.json");
        fruitShop.getSpoiledAndAvailableFruits("24-05-2012");
        fruitShop.getSpoiledAndAvailableFruits("24-05-2012", TypeOfFruit.KIWI);
        fruitShop.getAddedFruits("18-05-2012");
        fruitShop.getAddedFruits("23-05-2012", TypeOfFruit.KIWI);
    }
}
