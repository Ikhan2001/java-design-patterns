package design.pattern.structural.adapterpattern;

public class AdapterExample {
    public static void main(String[] args) {
        RazorpayGateway gateway = new RazorpayGateway();
        PaymentProcessor paymentProcessor = new RazorPayAdapter(gateway);
        paymentProcessor.pay(1000);
    }
}
