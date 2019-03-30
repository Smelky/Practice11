
import java.util.List;

public class DeliveryList {
    private List<Fruit> fruits;

    public DeliveryList() {
    }

    public DeliveryList delivery(List fruitsForDelivery) {
        fruits = fruitsForDelivery;
        return this;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }
}
