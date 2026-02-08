package main.lld.ParkingLot.FeeStrategy;

import java.time.Instant;

public interface ParkingFeeStrategy {
    Double calculateFee(Instant entryTime, Instant exitTime);
}
