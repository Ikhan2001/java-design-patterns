package design.pattern.structural.decoraterpattern.decorator;

import design.pattern.structural.decoraterpattern.Notification;

public class SmsDecorator extends NotificationDecorator{

    public SmsDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send() {
        notification.send();
        System.out.println("Sending SMS Notification");
    }
}
