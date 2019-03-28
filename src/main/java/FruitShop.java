import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FruitShop {
    private static final Logger LOGGER = Logger.getLogger(FruitShop.class);
    private List<DeliveryList> fruitData = new ArrayList<>();

    public void addFruits(String pathToJsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathToJsonFile);
        DeliveryList deliveryList = null;
        try {
            deliveryList = objectMapper.readValue(file, DeliveryList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fruitData.add(deliveryList);
    }

    public void save(String pathToJsonFile) {
        ObjectMapper mapper = new ObjectMapper();
        for (DeliveryList deliveryList : fruitData) {
            try {
                mapper.writeValue(new File(pathToJsonFile), deliveryList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(String pathToJsonFile) {
        fruitData.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathToJsonFile);
        DeliveryList deliveryList = null;
        try {
            deliveryList = objectMapper.readValue(file, DeliveryList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fruitData.add(deliveryList);
    }

    public List<Fruit> getSpoiledAndAvailableFruits(String expirationDate) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(0).fruits);
        List<Fruit> spoiledFruits = new ArrayList<>();
        List<Fruit> availableFruits = new ArrayList<>();
        long dateDifference = 0;
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Fruit fruit : fruits) {
            long expirationDateOfProduct = Long.parseLong(fruit.expirationDate);
            dateDifference = date.getTime() - fruit.dateOfDelivery.getTime();
            int differenceInDays = (int) (dateDifference / (24 * 60 * 60 * 1000));
            if (differenceInDays < expirationDateOfProduct) {
                spoiledFruits.add(fruit);
                LOGGER.info("Spoiled fruit from this delivery: " + fruit.typeOfFruits);
            } else {
                availableFruits.add(fruit);
                LOGGER.info("Available to sell: " + fruit.typeOfFruits);
            }
        }
        return spoiledFruits;
    }

    public List<Fruit> getSpoiledAndAvailableFruits(String expirationDate, TypeOfFruit typeOfFruit) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(0).fruits);
        List<Fruit> spoiledFruits = new ArrayList<>();
        List<Fruit> availableFruits = new ArrayList<>();
        long dateDifference = 0;
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Fruit fruit : fruits) {
            if (fruit.typeOfFruits.equals(typeOfFruit)) {
                long expirationDateOfProduct = Long.parseLong(fruit.expirationDate);
                dateDifference = date.getTime() - fruit.dateOfDelivery.getTime();
                int differenceInDays = (int) (dateDifference / (24 * 60 * 60 * 1000));
                if (differenceInDays < expirationDateOfProduct) {
                    spoiledFruits.add(fruit);
                    LOGGER.info("Spoiled fruit from this delivery: " + fruit.typeOfFruits);
                } else {
                    availableFruits.add(fruit);
                    LOGGER.info("Available to sell: " + fruit.typeOfFruits);
                }
            }
        }
        return spoiledFruits;
    }

    public List<Fruit> getAddedFruits(String dateOfDelivery) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(0).fruits);
        List<Fruit> fruitsWhatWeNeed = new ArrayList<>();
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(dateOfDelivery);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).dateOfDelivery.equals(dateConfigure(date))) {
                fruitsWhatWeNeed.add(fruits.get(i));
                LOGGER.info("Fruit delivered on this date: " + fruits.get(i).typeOfFruits);
            }
        }
        return fruitsWhatWeNeed;
    }

    public List<Fruit> getAddedFruits(String dateOfDelivery, TypeOfFruit typeOfFruit) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(0).fruits);
        List<Fruit> fruitsWhatWeNeed = new ArrayList<>();
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(dateOfDelivery);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).dateOfDelivery.equals(dateConfigure(date)) & fruits.get(i).typeOfFruits.equals(typeOfFruit)) {
                fruitsWhatWeNeed.add(fruits.get(i));
                int numOfFruits = 0;
                numOfFruits++;
                LOGGER.info("Num of " + typeOfFruit + " delivered on this date : " + numOfFruits);
            }
        }
        return fruitsWhatWeNeed;
    }

    private Date dateConfigure(Date dt) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.HOUR, 2);
        dt = c.getTime();
        return dt;
    }
}
