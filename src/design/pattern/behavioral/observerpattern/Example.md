# Observer Pattern - Example

## Project Structure

```text id="2ffj7t"
observerpattern
│
├── observer
│   ├── Observer.java
│   └── EmailSubscriber.java
│
├── subject
│   ├── SubjectImpl.java
│   ├── BlogPublisher.java
│   ├── NewsPublisher.java
│   └── VideoPublisher.java
│
└── BlogExample.java
```

---

# Observer Interface

```java id="sd6eh4"
public interface Observer {

    void update(String message);
}
```

---

# Email Subscriber

```java id="0fvl4y"
public class EmailSubscriber implements Observer {

    private String name;

    public EmailSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(
                name + " received: " + message
        );
    }
}
```

---

# Abstract Subject

Common functionality shared by all publishers.

```java id="wxfwyz"
public abstract class SubjectImpl {

    List<Observer> observers =
            new ArrayList<>();

    public void subscribe(
            Observer observer) {

        observers.add(observer);
    }

    public void unsubscribe(
            Observer observer) {

        observers.remove(observer);
    }

    public void notifyObservers(
            String message) {

        observers.forEach(
                observer ->
                        observer.update(message)
        );
    }

    public abstract void publish(
            String message);
}
```

---

# Blog Publisher

```java id="8ehovc"
public class BlogPublisher
        extends SubjectImpl {

    @Override
    public void publish(String post){

        System.out.println(
                "New Blog Published : " + post
        );

        notifyObservers(post);
    }
}
```

---

# News Publisher

```java id="6lbv64"
public class NewsPublisher
        extends SubjectImpl {

    @Override
    public void publish(String news){

        System.out.println(
                "News is published"
        );

        notifyObservers(news);
    }
}
```

---

# Video Publisher

```java id="7fzqns"
public class VideoPublisher
        extends SubjectImpl {

    @Override
    public void publish(String video){

        System.out.println(
                "Video Uploaded"
        );

        notifyObservers(video);
    }
}
```

---

# Client

```java id="9gjy8x"
public class BlogExample {

    public static void main(String[] args) {

        SubjectImpl publisher =
                new BlogPublisher();

        Observer user1 =
                new EmailSubscriber("John");

        Observer user2 =
                new EmailSubscriber("David");

        publisher.subscribe(user1);
        publisher.subscribe(user2);

        publisher.publish(
                "Observer Pattern Tutorial"
        );

        System.out.println(
                "________ News Publisher ________"
        );

        SubjectImpl news =
                new NewsPublisher();

        news.subscribe(user1);

        news.publish(
                "This is new news !!!!!"
        );

        System.out.println(
                "________ Video Publisher ________"
        );

        SubjectImpl video =
                new VideoPublisher();

        video.subscribe(user1);

        video.publish(
                "This is new video !!!!!"
        );
    }
}
```

---

# Output

```text id="wxv5g4"
New Blog Published : Observer Pattern Tutorial

John received: Observer Pattern Tutorial
David received: Observer Pattern Tutorial

________ News Publisher ________

News is published

John received: This is new news !!!!!

________ Video Publisher ________

Video Uploaded

John received: This is new video !!!!!
```

---

# Flow

```text id="gpdjlwm"
BlogPublisher
      |
      +--> John
      +--> David

Publish Blog
      |
      v

John receives update
David receives update

--------------------------------

NewsPublisher
      |
      +--> John

Publish News
      |
      v

John receives update

--------------------------------

VideoPublisher
      |
      +--> John

Publish Video
      |
      v

John receives update
```

---

# Key Learning

```text id="8y9pnn"
SubjectImpl
     ↑
     |
--------------------------------
|              |              |
BlogPublisher  NewsPublisher  VideoPublisher
```

All publishers reuse:

* subscribe()
* unsubscribe()
* notifyObservers()

Only publish() behavior changes.

This makes adding a new publisher easy:

```java id="ycslpy"
public class PaymentPublisher
        extends SubjectImpl {

    @Override
    public void publish(String message) {

        System.out.println(
                "Payment Event"
        );

        notifyObservers(message);
    }
}
```

No existing code needs modification.
