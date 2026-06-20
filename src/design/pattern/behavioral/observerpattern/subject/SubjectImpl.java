package design.pattern.behavioral.observerpattern.subject;

import design.pattern.behavioral.observerpattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class SubjectImpl{
    List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer );
    }

    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.update(message));
    }

    //for all
    public abstract void publish(String message);

}
