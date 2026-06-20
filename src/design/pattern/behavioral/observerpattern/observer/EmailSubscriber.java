package design.pattern.behavioral.observerpattern.observer;

public class EmailSubscriber implements Observer {

    private String name;

    public EmailSubscriber(String name){
        this.name = name;
    }

    @Override
    public void update(String post) {
        System.out.println(name + " received notification: " + post);
    }
}