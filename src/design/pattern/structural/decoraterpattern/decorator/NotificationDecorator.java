package design.pattern.structural.decoraterpattern.decorator;

import design.pattern.structural.decoraterpattern.Notification;

public abstract class NotificationDecorator implements Notification {
    protected Notification notification;

    public NotificationDecorator(Notification notification){
        this.notification = notification;
    }
}
