package design.pattern.structural.decoraterpattern.decorator;

import design.pattern.structural.decoraterpattern.Notification;

public class WhatsAppDecorator extends NotificationDecorator{

    public WhatsAppDecorator(Notification notification){
        super(notification);
    }

    @Override
    public void send() {
        notification.send();
        System.out.println("Sending WhatsApp Notification");
    }
}
