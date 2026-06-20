package design.pattern.behavioral.observerpattern.subject;

import design.pattern.behavioral.observerpattern.observer.Observer;

public interface Subject {
    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers();
}
