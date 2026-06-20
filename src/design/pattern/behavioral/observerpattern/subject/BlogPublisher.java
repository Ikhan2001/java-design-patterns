package design.pattern.behavioral.observerpattern.subject;
import design.pattern.behavioral.observerpattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BlogPublisher implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private String latestPost;

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(latestPost);
        }
    }

    public void publishPost(String post){
        this.latestPost = post;
        System.out.println("New Blog Published : " + post);
        notifyObservers();
    }
}