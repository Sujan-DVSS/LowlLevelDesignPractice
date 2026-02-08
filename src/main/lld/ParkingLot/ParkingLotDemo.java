package main.lld.ParkingLot;

import main.lld.ParkingLot.Models.ParkingSpot;
import main.lld.ParkingLot.Models.ParkingTicket;
import main.lld.ParkingLot.Models.Vehicle;
import main.lld.ParkingLot.Models.VehicleType;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ParkingLotDemo {
    public static void main(String[] args) throws InterruptedException {
        List<ParkingSpot> parkingSpots = getParkingSpots();
        ParkingLot parkingLot = ParkingLot.parkingLot();
        parkingLot.addParkingSpots(parkingSpots);

        Vehicle vehicle = new Vehicle("TS10CG9615", VehicleType.BIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        Thread.sleep(Duration.ofMinutes(2));
        parkingLot.unPark(ticket, "CASH");

    }
    private static List<ParkingSpot> getParkingSpots(){
        return Arrays.asList(new ParkingSpot("1", "1", VehicleType.BIKE, false), new ParkingSpot("2", "1", VehicleType.CAR, false));
    }
}
