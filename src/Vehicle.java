import java.util.HashMap;
import java.util.Map;
public class Vehicle {
    String licence;
    enum Type{
        MOTORCYCLE,
        CAR,
        TRUCK
    }
    Type type;
    static Map<String,Vehicle> searchV = new HashMap<String, Vehicle>();
    public Vehicle(String licence, Type type){
        // properties

        // constructor
        this.licence = licence;
        this.type = type;
    }
    // methods

}
