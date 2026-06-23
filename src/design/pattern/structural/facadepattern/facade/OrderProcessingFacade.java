package design.pattern.structural.facadepattern.facade;

import design.pattern.structural.facadepattern.InventoryService;
import design.pattern.structural.facadepattern.NotificationService;
import design.pattern.structural.facadepattern.PaymentService;
import design.pattern.structural.facadepattern.ShippingService;

public class OrderProcessingFacade {
    private InventoryService inventoryService = new InventoryService();
    private PaymentService paymentService = new PaymentService();
    private ShippingService shippingService = new ShippingService();
    private NotificationService notificationService = new NotificationService();

    public void placeOrder() {
        inventoryService.checkStock();
        paymentService.processPayment();
        shippingService.shipOrder();
        notificationService.sendNotification();
    }
}
