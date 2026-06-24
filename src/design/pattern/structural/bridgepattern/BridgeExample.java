package design.pattern.structural.bridgepattern;

import design.pattern.structural.bridgepattern.notification.AlertNotification;
import design.pattern.structural.bridgepattern.notification.Notification;
import design.pattern.structural.bridgepattern.notification.ReminderNotification;

public class BridgeExample {
    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        Notification alert = new AlertNotification(email);
        alert.notifyUser("Premium is due");

    }
}
