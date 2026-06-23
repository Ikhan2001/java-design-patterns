package design.pattern.structural.adapterpattern;

public class RazorpayGateway {

    public void makePayment(double amount) {
        System.out.println("Payment Done Via Razorpay : " + amount);
    }
}
