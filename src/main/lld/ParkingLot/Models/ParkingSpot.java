package main.lld.ParkingLot.Models;

public class ParkingSpot {
    private String spotNumber;
    private String floorNumber;
    private VehicleType vehicleType;
    private boolean isOccupied;

    public ParkingSpot(String spotNumber, String floorNumber, VehicleType vehicleType, boolean isOccupied) {
        this.spotNumber = spotNumber;
        this.floorNumber = floorNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = isOccupied;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
