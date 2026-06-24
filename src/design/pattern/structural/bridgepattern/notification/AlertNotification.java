package design.pattern.structural.bridgepattern.notification;

import design.pattern.structural.bridgepattern.MessageSender;

public class AlertNotification extends Notification{

    public AlertNotification(MessageSender sender){
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.sendMessage("[Alert] : "+message);
    }
}
