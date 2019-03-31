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
    private int numOfDelivery;

    public void setNumOfDelivery(int numOfDelivery) {
        this.numOfDelivery = numOfDelivery;
    }

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
            try {
                mapper.writeValue(new File(pathToJsonFile), fruitData.get(numOfDelivery));
            } catch (IOException e) {
                e.printStackTrace();
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

    public List<Fruit> getSpoiledFruits(String expirationDate) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(numOfDelivery).getFruits());
        System.out.println(fruits.size());
        List<Fruit> spoiledFruits = new ArrayList<>();
        long dateDifference;
        LOGGER.info("Spoiled fruits for " + expirationDate + " from this delivery: ");
        for (Fruit fruit : fruits) {
            long expirationDateOfProduct = Long.parseLong(fruit.getExpirationDate());
            dateDifference = changeDateFormat(expirationDate).getTime() - fruit.getDateOfDelivery().getTime();
            int differenceInDays = (int) (dateDifference / (24 * 60 * 60 * 1000));
            if (differenceInDays < expirationDateOfProduct) {
                spoiledFruits.add(fruit);
                LOGGER.info(fruit.getTypeOfFruits());
            }
        }
        return spoiledFruits;
    }

    public List<Fruit> getAvailableFruits(String expirationDate) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(numOfDelivery).getFruits());
        List<Fruit> availableFruits = new ArrayList<>();
        long dateDifference;
        LOGGER.info("Available fruits for " + expirationDate + " to sell from this delivery: ");
        for (Fruit fruit : fruits) {
            long expirationDateOfProduct = Long.parseLong(fruit.getExpirationDate());
            dateDifference = changeDateFormat(expirationDate).getTime() - fruit.getDateOfDelivery().getTime();
            int differenceInDays = (int) (dateDifference / (24 * 60 * 60 * 1000));
            if (differenceInDays >= expirationDateOfProduct) {
                availableFruits.add(fruit);
                LOGGER.info(fruit.getTypeOfFruits());
            }
        }
        return availableFruits;
    }

    public List<Fruit> getSpoiledFruits(String expirationDate, TypeOfFruit typeOfFruit) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(numOfDelivery).getFruits());
        List<Fruit> spoiledFruits = new ArrayList<>();
        long dateDifference;
        for (Fruit fruit : fruits) {
            if (fruit.getTypeOfFruits().equals(typeOfFruit)) {
                long expirationDateOfProduct = Long.parseLong(fruit.getExpirationDate());
                dateDifference = changeDateFormat(expirationDate).getTime() - fruit.getDateOfDelivery().getTime();
                int differenceInDays = (int) (dateDifference / (24 * 60 * 60 * 1000));
                if (differenceInDays < expirationDateOfProduct) {
                    spoiledFruits.add(fruit);
                }
            }
        }
        LOGGER.info("Num of spoiled " + typeOfFruit + " for " + expirationDate + " from this delivery is: " + spoiledFruits.size());
        return spoiledFruits;
    }

    public List<Fruit> getAvailableFruits(String expirationDate, TypeOfFruit typeOfFruit) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(numOfDelivery).getFruits());
        List<Fruit> availableFruits = new ArrayList<>();
        long dateDifference;
        for (Fruit fruit : fruits) {
            if (fruit.getTypeOfFruits().equals(typeOfFruit)) {
                long expirationDateOfProduct = Long.parseLong(fruit.getExpirationDate());
                dateDifference = changeDateFormat(expirationDate).getTime() - fruit.getDateOfDelivery().getTime();
                int differenceInDays = (int) (dateDifference / (24 * 60 * 60 * 1000));
                if (differenceInDays >= expirationDateOfProduct) {
                    availableFruits.add(fruit);
                }
            }
        }
        LOGGER.info("Num of available to sell " + typeOfFruit + " for " + expirationDate + " is: " + availableFruits.size());
        return availableFruits;
    }

    public List<Fruit> getAddedFruits(String dateOfDelivery) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(numOfDelivery).getFruits());
        List<Fruit> fruitsWhatWeNeed = new ArrayList<>();
        LOGGER.info("Fruit delivered on " + dateOfDelivery + " : ");
        for (Fruit fruit : fruits) {
            if (fruit.getDateOfDelivery().equals(changeDateFormat(dateOfDelivery))) {
                fruitsWhatWeNeed.add(fruit);
                LOGGER.info(fruit.getTypeOfFruits());
            }
        }
        if (fruitsWhatWeNeed.size() == 0) {
            LOGGER.info("None");
        }
        return fruitsWhatWeNeed;
    }

    public List<Fruit> getAddedFruits(String dateOfDelivery, TypeOfFruit typeOfFruit) {
        List<Fruit> fruits = new ArrayList<>(fruitData.get(numOfDelivery).getFruits());
        List<Fruit> fruitsWhatWeNeed = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getDateOfDelivery().equals(changeDateFormat(dateOfDelivery)) & fruit.getTypeOfFruits().equals(typeOfFruit)) {
                fruitsWhatWeNeed.add(fruit);
                LOGGER.info("Num of " + typeOfFruit + " delivered on " + dateOfDelivery + " : " + fruitsWhatWeNeed.size());
            }
        }
        if (fruitsWhatWeNeed.size() == 0) {
            LOGGER.info("None");
        }
        return fruitsWhatWeNeed;
    }

    private Date changeDateFormat(String expirationDate) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateConfigure(date);
    }

    private Date dateConfigure(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 2);
        date = calendar.getTime();
        return date;
    }
}
