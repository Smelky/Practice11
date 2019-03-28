import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fruit {
    public TypeOfFruit typeOfFruits;
    public String expirationDate;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date dateOfDelivery;
    public int price;

    public Fruit(TypeOfFruit typeOfFruits, String expirationDate, String dateOfDelivery, int price) {
        this.typeOfFruits = typeOfFruits;
        this.expirationDate = expirationDate;
        this.dateOfDelivery = changeTimeFormat(dateOfDelivery);
        this.price = price;
    }

    private Date changeTimeFormat(String toParse) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(toParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateConfigure(date);
    }

    private Date dateConfigure(Date dt) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        return dt;
    }

    public Fruit() {
    }
}
