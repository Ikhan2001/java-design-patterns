package design.pattern.behavioral.strategypattern;

public class PaypalPayment implements PaymentStrategy{

    @Override
    public void pay(double amount) {
        System.out.println("Paid "+ amount + " using Paypal");
    }
}
