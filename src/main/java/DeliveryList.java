
import java.util.List;

public class DeliveryList<Fruit> {
    private List<Fruit> fruits;

    public DeliveryList() {
    }

    public DeliveryList<Fruit> delivery(List fruitsForDelivery) {
        fruits = fruitsForDelivery;
        return this;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }
}
