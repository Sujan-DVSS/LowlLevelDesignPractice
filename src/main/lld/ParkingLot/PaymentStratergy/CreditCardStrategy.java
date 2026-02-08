package main.lld.ParkingLot.PaymentStratergy;

public class CreditCardStrategy implements IPaymentStrategy{
    @Override
    public boolean pay(Double amount) {
        System.out.println("Amount: " + amount + " has been debited from your credit card");
        return true;
    }
}
