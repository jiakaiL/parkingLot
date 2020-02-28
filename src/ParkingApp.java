import java.util.Scanner;

public class ParkingApp {

    public static void main (String[] args){
        int spot_number = 1000;
        System.out.println("<<<<<<<<<<<<<<<<<<<");
        System.out.println("Enter Parking Lot");

        Scanner sc_lot = new Scanner(System.in);
        String zip = sc_lot.nextLine();
        ParkingLot pt1 = new ParkingLot(zip);

        System.out.println("creating Parking Lot at: " + zip);

        System.out.println("<<<<<<<<<<<<<<<<<<<");
        System.out.println("Enter spots");
        spotOptions();
        Scanner sc_spot = new Scanner(System.in);

        // only enter number for now
        while (sc_spot.hasNext()){
            if (sc_spot.hasNextInt()){
                // creating spots in different sizes
                int input = sc_spot.nextInt();
                spot_number++;
                if (input == 1){
                    Spot s = new Spot(spot_number, Vehicle.Type.MOTORCYCLE);
                    pt1.addSpot(s);
                    System.out.println("Small Spot Created");
                    spotOptions();
                }
                else if (input == 2){
                    Spot m = new Spot(spot_number, Vehicle.Type.CAR);
                    pt1.addSpot(m);
                    System.out.println("Medium Spot Created");
                    spotOptions();
                }
                else if (input == 3){
                    Spot l = new Spot(spot_number, Vehicle.Type.TRUCK);
                    pt1.addSpot(l);
                    System.out.println("Large Spot Created");
                    spotOptions();
                }
                else if (input == 0){
                    break;
                }
                else{
                    System.out.println("Wrong Int, Enter Again");
                    spotOptions();
                }
            }
            else {
                System.out.println("Enter Int, Please!");
                spotOptions();
                sc_spot.next();
            }
        }
        System.out.println("End creating spots");
        // show parking lot info
        pt1.showInfo();

        // After creating parking lot, now start parking!
        commandOptions();

        Scanner command = new Scanner(System.in);
        // enter int input only
        while(command.hasNext()){
            if(command.hasNextInt()){
                // proceeding with chosen command
                int input_command = command.nextInt();
                if (input_command == 1){
                    vehicleTypes();
                    Scanner type = new Scanner(System.in);
                    while (type.hasNext()){
                        if (type.hasNextInt()){
                            int input_type = type.nextInt();
                            if (input_type == 1){
                                parkMotor(pt1);
                                break;
                            }
                            else if (input_type==2){
                                parkCar(pt1);
                                break;
                            }
                            else if (input_type == 3){
                                parkTruck(pt1);
                                break;
                            }
                            else{
                                System.out.println("Enter Correct input");
                                vehicleTypes();
                                type.next();
                            }
                        }
                        else {
                            System.out.println("Enter Int Please");
                            vehicleTypes();
                            type.next();
                        }
                    }
                    commandOptions();
                }
                else if (input_command == 2){
                    if (pt1.occupied.isEmpty()){
                        System.out.println("Empty");
                        commandOptions();
                    }
                    else{
                        removeVehicle(pt1);
                        commandOptions();
                    }
                }
                else if (input_command ==3){
                    pt1.showInfo();
                    pt1.showOccupied();
                    commandOptions();
                }
                else if (input_command == 0){
                    // Command 0 is entered, so end this program
                    return;
                }
                else{
                    commandOptions();
                }
            }
            else{
                System.out.println("Enter Int for Command");
                commandOptions();
                command.next();
            }
        }
    }

    private static void spotOptions(){
        System.out.println("1-Motorcycle" +
                "\n 2-Car" +
                "\n 3-Truck" + "" +
                "\n 0-End");
    }
    private static void commandOptions(){
        System.out.println("Option" +
                "\n 1-Park a vehicle" +
                "\n 2-Remove a vehicle"+
                "\n 3-Show info" +
                "\n 0-Exit");
    }
    private static void vehicleTypes(){
        System.out.println("1-Motorcycle" +
                "\n 2-Car" +
                "\n 3-Truck");
    }
    private static void parkMotor(ParkingLot pt){
        System.out.println("Now parking MotorCycle");
        System.out.println("Enter licence");
        Scanner licence = new Scanner(System.in);
        Vehicle motor = new Vehicle(licence.nextLine(), Vehicle.Type.MOTORCYCLE);
        pt.addVehicle(motor);
        pt.parkVehicle(motor);
        System.out.println("Motorcycle Parked");
    }
    private static void parkCar(ParkingLot pt){
        System.out.println("Now parking Car");
        System.out.println("Enter licence");
        Scanner licence = new Scanner(System.in);
        Vehicle car = new Vehicle(licence.nextLine(), Vehicle.Type.CAR);
        pt.addVehicle(car);
        pt.parkVehicle(car);
        System.out.println("Car Parked");
    }
    private static void parkTruck(ParkingLot pt){
        System.out.println("Now parking Truck");
        System.out.println("Enter licence");
        Scanner licence = new Scanner(System.in);
        Vehicle truck = new Vehicle(licence.nextLine(), Vehicle.Type.TRUCK);
        pt.addVehicle(truck);
        pt.parkVehicle(truck);
        System.out.println("Truck Parked");
    }
    private static void removeVehicle(ParkingLot pt){
        System.out.println("Currently Parking");
        System.out.println("Enter Licence for removal");
        pt.showOccupied();
        Scanner licence = new Scanner(System.in);
        String str_licence = licence.nextLine();
        while(!pt.searchV.keySet().contains(str_licence)){
            pt.showOccupied();
            System.out.println("Please Enter Correct Licence");
            str_licence = licence.nextLine();
        }
        Vehicle v = pt.searchV.get(str_licence);
        pt.removeVehicle(v);
        System.out.println("Licence: " + str_licence + " removed");
    }
}
