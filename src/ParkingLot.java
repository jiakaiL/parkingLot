
import java.util.*;

public class ParkingLot {
    // properties for ParkingLot
    public String zip; // unique postal code
    public int s,m,l; // number of spots available for different sizes
    public Map<Vehicle,Spot> occupied = new HashMap<>();
    public Stack<Spot> small = new Stack<Spot>();
    public Stack<Spot> medium = new Stack<Spot>();
    public Stack<Spot> large = new Stack<Spot>();
    public Stack<Vehicle> waitingSmall = new Stack<Vehicle>();
    public Stack<Vehicle> waitingMedium = new Stack<Vehicle>();
    public Stack<Vehicle> waitingLarge = new Stack<Vehicle>();
    static Map<String,Vehicle> searchV = new HashMap<String, Vehicle>();


    public ParkingLot(String zip){
        // constructor
        this.zip = zip;
    }

    // Methods
    // Add a spot to specific stack
    public void addSpot(Spot spot){
        switch(spot.type){
            case MOTORCYCLE:
                small.add(spot);
                s+=1;
                break;
            case CAR:
                medium.add(spot);
                m+=1;
                break;
            case TRUCK:
                large.add(spot);
                l+=1;
                break;
        }
    }

    public int getTotal(){
        int total = this.s+this.m+this.l;
        return total;
    }

    public void parkVehicle (Vehicle v){
        checkingOnWaiting();
        parkingNew(v);
    }

    public void parkingNew(Vehicle v){
        System.out.println("<<<<<<<<<>>>>>>>>>>>>>");
        System.out.println("Parking New car");
        switch(v.type){
            case MOTORCYCLE:
                if (!small.empty()){
                    Spot spot = small.pop();
                    occupied.put(v,spot);
                    s-=1;
                    break;
                }
                else if (!medium.empty()){
                    Spot spot = medium.pop();
                    occupied.put(v,spot);
                    m-=1;
                    break;
                }
                else if (!large.empty()){
                    Spot spot = large.pop();
                    occupied.put(v,spot);
                    l-=1;
                    break;
                }
                else{
                    insert_at_btm(v,waitingSmall);
                    System.out.println("Full at " + v.type + "! Add "+v.licence + " to waitinglist");
                    break;
                }
            case CAR:
                if (!medium.empty()){
                Spot spot = medium.pop();
                occupied.put(v,spot);
                m-=1;
                break;
            }
            else if (!large.empty()){
                Spot spot = large.pop();
                occupied.put(v,spot);
                l-=1;
                break;
            }
            else{
                insert_at_btm(v,waitingMedium);
                System.out.println("Full at " + v.type + "! Add "+v.licence + " to waitinglist");
                break;
            }
            case TRUCK:
                if (!large.empty()){
                    Spot spot = large.pop();
                    occupied.put(v,spot);
                    l-=1;
                    break;
                }
                else{
                    insert_at_btm(v,waitingLarge);
                    System.out.println("Full at " + v.type + "! Add "+v.licence + " to waitinglist");
                    break;
                }
        }
    }

    public void removeVehicle(Vehicle v){
        System.out.println("<<<<<<<<<>>>>>>>>>>>>>");
        System.out.println("removing car");
        Spot spot = occupied.get(v);
        occupied.remove(v);
        addSpot(spot);
        checkingOnWaiting();
    }
    public void checkingOnWaiting(){
        if (!waitingSmall.empty()){
            if (isSpotAvailable(Vehicle.Type.MOTORCYCLE)){
                Vehicle first = waitingSmall.pop();
                parkingWaiting(first);
            }
        }
        if (!waitingMedium.empty()){
            if (isSpotAvailable(Vehicle.Type.CAR)){
                Vehicle first = waitingMedium.pop();
                parkingWaiting(first);
            }
        }
        if (!waitingLarge.empty()){
            if (isSpotAvailable(Vehicle.Type.TRUCK)){
                Vehicle first = waitingLarge.pop();
                parkingWaiting(first);
            }
        }
    }
    public void parkingWaiting(Vehicle v){
        System.out.println("<<<<<<<<<>>>>>>>>>>>>>");
        System.out.println("Parking waiting car");
        switch(v.type){
            case MOTORCYCLE:
                if (!small.empty()){
                    Spot spot = small.pop();
                    occupied.put(v,spot);
                    s-=1;
                    break;
                }
                else if (!medium.empty()){
                    Spot spot = medium.pop();
                    occupied.put(v,spot);
                    m-=1;
                    break;
                }
                else if (!large.empty()){
                    Spot spot = large.pop();
                    occupied.put(v,spot);
                    l-=1;
                    break;
                }
            case CAR:
                if (!medium.empty()){
                    Spot spot = medium.pop();
                    occupied.put(v,spot);
                    m-=1;
                    break;
                }
                else if (!large.empty()){
                    Spot spot = large.pop();
                    occupied.put(v,spot);
                    l-=1;
                    break;
                }
            case TRUCK:
                if (!large.empty()){
                    Spot spot = large.pop();
                    occupied.put(v,spot);
                    l-=1;
                    break;
                }
        }
        showInfo();
        showOccupied();
    }

    public boolean isSpotAvailable(Vehicle.Type type){
        switch (type){
            case MOTORCYCLE :
                return (s>0 || m>0 || l>0);
            case CAR:
                return (m>0 || l>0);
            case TRUCK:
                return l>0;
        }
        return false;
    }

    // Reference GeeksforGeeks


    public void insert_at_btm(Vehicle v, Stack<Vehicle> st){
        if (st.isEmpty()){
            st.push(v);
        }
        else{
            Vehicle vehicle = st.peek();
            st.pop();
            insert_at_btm(v, st);
            st.push(vehicle);
        }
    }

    public void showInfo(){
        System.out.println("Parking Lot is at: " + zip +
                "\nAvailable Spots: " + getTotal()+
                "\n small: "+ this.s +
                "\n medium: "+ this.m+
                "\n large: "+ this.l +
                "\n Waiting Small: " + waitingSmall.size() +
                "\n Waiting Medium: " + waitingMedium.size() +
                "\n Waiting Large: " + waitingLarge.size());
    }
    public void showOccupied(){
        List<String> licences = new LinkedList<>();
        for (Vehicle v: occupied.keySet()){
            licences.add(v.licence);
        }
        System.out.println("Now Parking: "+licences);
    }
    public void addVehicle(Vehicle v){
        this.searchV.put(v.licence,v);
    }

}
