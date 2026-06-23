package design.pattern.structural.decoraterpattern.decorator;

import design.pattern.structural.decoraterpattern.Notification;

public class PushNotificationDecorator extends NotificationDecorator{

    public PushNotificationDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send() {
        notification.send();
        System.out.println("Sending Push Notification");
    }
}
