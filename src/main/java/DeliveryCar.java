import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DeliveryCar {

    public void delivery(DeliveryList delivery, String numOfDelivery) {
        String path = "src/main/resources/" + numOfDelivery + "Delivery.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), delivery);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
