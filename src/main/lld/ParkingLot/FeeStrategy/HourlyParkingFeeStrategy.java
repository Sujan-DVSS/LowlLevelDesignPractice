package main.lld.ParkingLot.FeeStrategy;

import java.time.Duration;
import java.time.Instant;

public class HourlyParkingFeeStrategy implements ParkingFeeStrategy {
    @Override
    public Double calculateFee(Instant entryTime, Instant exitTime) {
        double perHourFee = 20;
        Duration duration = Duration.between(entryTime, exitTime);
        double hours = duration.toHours();
        double minutes = duration.toMinutes() % 60;

        return hours * 20 + (minutes / 60) * 20;
    }
}
