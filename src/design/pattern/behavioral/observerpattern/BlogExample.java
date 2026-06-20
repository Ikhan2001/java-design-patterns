package design.pattern.behavioral.observerpattern;

import design.pattern.behavioral.observerpattern.observer.EmailSubscriber;
import design.pattern.behavioral.observerpattern.observer.Observer;
import design.pattern.behavioral.observerpattern.subject.BlogPublisher;
import design.pattern.behavioral.observerpattern.subject.NewsPublisher;
import design.pattern.behavioral.observerpattern.subject.SubjectImpl;
import design.pattern.behavioral.observerpattern.subject.VideoPublisher;

public class BlogExample {
    public static void main(String[] args) {
        SubjectImpl publisher = new BlogPublisher();

        Observer user1 = new EmailSubscriber("John");
        Observer user2 = new EmailSubscriber("David");

        publisher.subscribe(user1);
        publisher.subscribe(user2);

        publisher.publish("Observer Pattern Tutorial");
        System.out.println("__________________New Publisher____________________");
        SubjectImpl news = new NewsPublisher();
        news.subscribe(user1);
        news.publish("This is new news !!!!!!!!!");
        System.out.println("__________________VideoPublisher____________________");
        SubjectImpl video = new VideoPublisher();
        video.subscribe(user1);
        video.publish("This is new video !!!!!!!!!");
    }
}
