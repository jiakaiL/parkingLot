import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    ParkingLot pt1 = new ParkingLot("V6P0C8");
    Spot s1 = new Spot(1111, Vehicle.Type.MOTORCYCLE);
    Spot s2 = new Spot(1112, Vehicle.Type.MOTORCYCLE);
    Spot m1 = new Spot(2221, Vehicle.Type.CAR);
    Spot l1 = new Spot(3331, Vehicle.Type.TRUCK);
    Vehicle motor1 = new Vehicle("a", Vehicle.Type.MOTORCYCLE);
    Vehicle motor2 = new Vehicle("c", Vehicle.Type.MOTORCYCLE);
    Vehicle car1 = new Vehicle ("b", Vehicle.Type.CAR);
    Vehicle car2 = new Vehicle ("d", Vehicle.Type.CAR);
    Vehicle truck1 = new Vehicle ("f", Vehicle.Type.TRUCK);

    @Test
    void parkVehicle() {
        pt1.addSpot(s1);
        pt1.addSpot(s2);
        pt1.addSpot(m1);


        // test parking new cars at available spot
        pt1.parkVehicle(motor1);
        assertEquals(motor1, pt1.occupied.keySet().toArray()[0]);

        // test parking new cars at unavailable spot
        pt1.parkVehicle(truck1);
        assertEquals(1,pt1.waitingLarge.size());

        // test parking second motorcycle, at the same time, large spot is available
        // so truck in the waiting list will be placed first
        // then motor2
        pt1.addSpot(l1);
        pt1.parkVehicle(motor2);
        assertEquals(true,pt1.waitingLarge.empty());
        assertEquals(truck1,pt1.occupied.keySet().toArray()[1]);
        assertEquals(motor2,pt1.occupied.keySet().toArray()[0]);

        pt1.parkVehicle(car1);
        pt1.parkVehicle(car2);
        assertEquals(4, pt1.occupied.size());
        assertEquals(car2, pt1.waitingMedium.pop());

    }

    @Test
    void removeVehicle() {
        pt1.addSpot(s1);
        pt1.parkVehicle(motor1);
        pt1.parkVehicle(motor2);
        pt1.removeVehicle(motor1);

        assertEquals(motor2,pt1.occupied.keySet().toArray()[0]);
    }
}