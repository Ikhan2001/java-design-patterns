package design.pattern.structural.bridgepattern;

public class EmailSender implements MessageSender{

    @Override
    public void sendMessage(String message) {
        System.out.println("Email : "+message);
    }
}
