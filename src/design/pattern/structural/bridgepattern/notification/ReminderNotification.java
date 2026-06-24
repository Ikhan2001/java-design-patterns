package design.pattern.structural.bridgepattern.notification;

import design.pattern.structural.bridgepattern.MessageSender;

public class ReminderNotification extends Notification {

    public ReminderNotification(MessageSender sender){
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.sendMessage("[Reminder]: "+message);
    }
}
