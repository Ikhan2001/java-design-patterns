package design.pattern.structural.decoraterpattern;

import design.pattern.structural.decoraterpattern.decorator.PushNotificationDecorator;
import design.pattern.structural.decoraterpattern.decorator.SmsDecorator;
import design.pattern.structural.decoraterpattern.decorator.WhatsAppDecorator;

public class NotificationExample {
    public static void main(String[] args) {
        Notification notification = new EmailNotification();
        notification = new SmsDecorator(notification);
        notification = new WhatsAppDecorator(notification);
        notification = new PushNotificationDecorator(notification);
        notification.send();
    }
}
