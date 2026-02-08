package main.lld.ParkingLot;

import main.lld.ParkingLot.FeeStrategy.HourlyParkingFeeStrategy;
import main.lld.ParkingLot.FeeStrategy.ParkingFeeStrategy;
import main.lld.ParkingLot.Manager.ParkingSpotManager;
import main.lld.ParkingLot.Manager.PaymentManager;
import main.lld.ParkingLot.Models.*;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParkingLot {

    private static ParkingLot parkingLot = null;
    private final ParkingSpotManager parkingSpotManager;
    private final ParkingFeeStrategy parkingFeeStrategy;
    private final PaymentManager paymentManager;
    private Map<VehicleType, List<ParkingSpot>> parkingSpotsByType;

    private ParkingLot() {
        parkingSpotManager = new
                ParkingSpotManager();
        parkingSpotsByType = new HashMap<>();
        paymentManager = new PaymentManager();
        parkingFeeStrategy = new HourlyParkingFeeStrategy();
    }

    public static ParkingLot parkingLot() {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    public ParkingTicket park(Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingSpotManager.parkVehicle(parkingSpotsByType, vehicle);
        if (parkingSpot == null) {
            return null;
        }
        parkingSpot.setOccupied(true);
        System.out.println("Your " + vehicle.getVehicleType().toString() + " having vehicle number: " + vehicle.getVehicleNumber() + " has been parked.");
        return new ParkingTicket(UUID.randomUUID().toString(), vehicle, parkingSpot, Instant.now());
    }

    public boolean unPark(ParkingTicket parkingTicket, String paymentType) {
        boolean unparked = parkingSpotManager.unParkVehicle(parkingTicket.getParkingSpot());
        if (unparked) {
            Instant exitTime = Instant.now();
            Double fee = parkingFeeStrategy.calculateFee(parkingTicket.getEntryTime(), exitTime);
            if(paymentManager.pay(fee, paymentType)){
                System.out.println("Payment is successful");
                System.out.println("Your " + parkingTicket.getVehicle().getVehicleType().toString() + " having vehicle number: " + parkingTicket.getVehicle().getVehicleNumber() + " has been unparked.");

            }else{
                System.out.println("Payment failed");
                return false;
            }
        }
        return true;
    }

    public Map<VehicleType, List<ParkingSpot>> getParkingSpotsByType() {
        return parkingSpotsByType;
    }

    public void addParkingSpots(List<ParkingSpot> parkingSpots) {
        for(ParkingSpot parkingSpot: parkingSpots){
            VehicleType vehicleType = parkingSpot.getVehicleType();
            if(!parkingSpotsByType.containsKey(vehicleType)){
                parkingSpotsByType.put(vehicleType, Arrays.asList(parkingSpot));
                System.out.println("Parking spot with spot id: " + parkingSpot.getSpotNumber() + " has been added successfully");

            }else{
                List<ParkingSpot> existingParkingSpots = parkingSpotsByType.get(vehicleType);
                Map<String, ParkingSpot> existingParkingSpotById = existingParkingSpots.stream().collect(Collectors.toMap(ParkingSpot::getSpotNumber, Function.identity()));
                if(existingParkingSpotById.containsKey(parkingSpot.getSpotNumber())){
                    System.out.println("Parking spot with spot id: " + parkingSpot.getSpotNumber() + " already exists. Cannot be added");
                }else{
                    System.out.println("Parking spot with spot id: " + parkingSpot.getSpotNumber() + " has been added successfully");
                    existingParkingSpots.add(parkingSpot);
                    parkingSpotsByType.put(vehicleType, existingParkingSpots);
                }
            }
        }
    }
}
