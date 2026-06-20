package design.pattern.behavioral.observerpattern;

import design.pattern.behavioral.observerpattern.observer.EmailSubscriber;
import design.pattern.behavioral.observerpattern.observer.Observer;
import design.pattern.behavioral.observerpattern.subject.BlogPublisher;

public class BlogExample {
    public static void main(String[] args) {
        BlogPublisher blog = new BlogPublisher();

        Observer user1 = new EmailSubscriber("John");
        Observer user2 = new EmailSubscriber("David");

        blog.subscribe(user1);
        blog.subscribe(user2);

        blog.publishPost("Observer Pattern Tutorial");
    }
}
