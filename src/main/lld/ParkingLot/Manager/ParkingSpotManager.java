package main.lld.ParkingLot.Manager;

import main.lld.ParkingLot.Models.ParkingSpot;
import main.lld.ParkingLot.Models.Vehicle;
import main.lld.ParkingLot.Models.VehicleType;

import java.util.List;
import java.util.Map;

public class ParkingSpotManager {

    public ParkingSpot parkVehicle(Map<VehicleType, List<ParkingSpot>> parkingSpotsByType, Vehicle vehicle){
        List<ParkingSpot> parkingSpots = parkingSpotsByType.get(vehicle.getVehicleType());
        ParkingSpot availableParkingSpot = null;
        for(ParkingSpot parkingSpot: parkingSpots){
            if(!parkingSpot.isOccupied()){
                return parkingSpot;
            }
        }
        return null;
    }
    public boolean unParkVehicle(ParkingSpot parkingSpot){
        parkingSpot.setOccupied(false);
        return true;
    }

}
