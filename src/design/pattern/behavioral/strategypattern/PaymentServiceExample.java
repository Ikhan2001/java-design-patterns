package design.pattern.behavioral.strategypattern;

public class PaymentServiceExample {
    public static void main(String[] args) {
        //payment strategy
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentStrategy UPI = new UpiPayment();
        PaymentStrategy paypal = new PaypalPayment();

        PaymentService paymentService = new PaymentService(paypal);
        paymentService.processPayment(1000);

    }
}
