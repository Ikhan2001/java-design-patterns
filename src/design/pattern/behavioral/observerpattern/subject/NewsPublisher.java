package design.pattern.behavioral.observerpattern.subject;

public class NewsPublisher extends SubjectImpl{

    @Override
    public void publish(String news){
        System.out.println("News is published");
        notifyObservers(news);
    }
}
