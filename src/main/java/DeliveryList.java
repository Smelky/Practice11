import java.util.List;

public class DeliveryList {
    public List<Fruit> fruits;

    public DeliveryList deliveryList(List fruitsForDelivery) {
        fruits = fruitsForDelivery;
        return this;
    }

    public DeliveryList() {
    }
}
