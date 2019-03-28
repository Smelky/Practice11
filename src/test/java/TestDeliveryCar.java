import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDeliveryCar {

    @Test
    public void testDeliveryCar() {
        List fruits = new ArrayList();
        fruits.add(new Fruit(TypeOfFruit.KIWI, "4", "14-05-2012", 40));
        fruits.add(new Fruit(TypeOfFruit.KIWI, "3", "23-05-2012", 40));
        fruits.add(new Fruit(TypeOfFruit.KIWI, "4", "16-05-2012", 40));
        fruits.add(new Fruit(TypeOfFruit.NECTARINE, "3", "18-05-2012", 60));
        fruits.add(new Fruit(TypeOfFruit.NECTARINE, "4", "15-05-2012", 60));
        fruits.add(new Fruit(TypeOfFruit.NECTARINE, "3", "20-05-2012", 60));
        fruits.add(new Fruit(TypeOfFruit.PLUM, "4", "20-05-2012", 29));
        fruits.add(new Fruit(TypeOfFruit.PLUM, "4", "24-05-2012", 29));
        fruits.add(new Fruit(TypeOfFruit.PLUM, "4", "18-05-2012", 29));
        fruits.add(new Fruit(TypeOfFruit.WATERMELON, "2", "13-05-2012", 35));
        fruits.add(new Fruit(TypeOfFruit.WATERMELON, "3", "16-05-2012", 35));
        fruits.add(new Fruit(TypeOfFruit.WATERMELON, "2", "18-05-2012", 35));
        fruits.add(new Fruit(TypeOfFruit.ORANGE, "5", "19-05-2012", 42));
        fruits.add(new Fruit(TypeOfFruit.ORANGE, "2", "22-05-2012", 42));
        fruits.add(new Fruit(TypeOfFruit.ORANGE, "4", "15-05-2012", 42));
        fruits.add(new Fruit(TypeOfFruit.APPLE, "5", "19-05-2012", 29));
        fruits.add(new Fruit(TypeOfFruit.APPLE, "4", "16-05-2012", 29));
        fruits.add(new Fruit(TypeOfFruit.APPLE, "7", "12-05-2012", 29));
        DeliveryCar deliveryCar = new DeliveryCar();
        DeliveryList deliveryList = new DeliveryList();
        deliveryCar.delivery(deliveryList.deliveryList(fruits), "First");
    }
}
