package design.pattern.structural.adapterpattern;

public class RazorPayAdapter implements PaymentProcessor{
    private RazorpayGateway gateway;

    public RazorPayAdapter(RazorpayGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void pay(double amount) {
        gateway.makePayment(amount);
    }
}
