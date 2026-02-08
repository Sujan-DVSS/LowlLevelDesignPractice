package main.lld.ParkingLot.Manager;

import main.lld.ParkingLot.PaymentStratergy.CashStrategy;
import main.lld.ParkingLot.PaymentStratergy.CreditCardStrategy;
import main.lld.ParkingLot.PaymentStratergy.IPaymentStrategy;

import java.util.Objects;

public class PaymentManager {
    private IPaymentStrategy paymentStrategy;

    public PaymentManager() {
    }

    private void setPaymentStrategy(String paymentType) {

        if (Objects.equals(paymentType, "CASH")) this.paymentStrategy = new CashStrategy();
        if (Objects.equals(paymentType, "CREDIT CARD")) this.paymentStrategy = new CreditCardStrategy();
    }

    public boolean pay(Double amount, String paymentType) {
        setPaymentStrategy(paymentType);
        return paymentStrategy.pay(amount);
    }

}
