package design.pattern.structural.bridgepattern.notification;

import design.pattern.structural.bridgepattern.MessageSender;

public abstract class Notification {
    protected MessageSender sender;

    public Notification(MessageSender sender){
        this.sender = sender;
    }

    public abstract void notifyUser(String message);
}
