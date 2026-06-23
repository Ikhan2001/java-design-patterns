package design.pattern.structural.facadepattern;

import design.pattern.structural.facadepattern.facade.OrderProcessingFacade;

public class FacadeExample {
    public static void main(String[] args) {
        OrderProcessingFacade facade = new OrderProcessingFacade();
        facade.placeOrder();
    }
}
