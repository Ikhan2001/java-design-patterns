package design.pattern.structural.decoraterpattern;

public class EmailNotification implements Notification{

    @Override
    public void send() {
        System.out.println("Sending email notification");
    }
}
