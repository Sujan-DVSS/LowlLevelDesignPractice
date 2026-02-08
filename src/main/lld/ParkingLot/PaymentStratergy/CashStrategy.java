package main.lld.ParkingLot.PaymentStratergy;

public class CashStrategy implements IPaymentStrategy{
    @Override
    public boolean pay(Double amount) {
        System.out.println("Thank you for paying: "+ amount + " as parking fee");
        return true;
    }
}
