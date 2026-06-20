# Observer Pattern

## Category

Behavioral Design Pattern

---

# Definition

The Observer Pattern defines a one-to-many dependency between objects so that when one object changes its state, all its dependents are automatically updated.

In simple words:

```text
ONE changes
    ↓
MANY get updated
```

---

# Real-Life Analogy

Think of a YouTube Channel.

```text
YouTube Channel
      |
      | New Video Uploaded
      |
      +--> Subscriber 1
      +--> Subscriber 2
      +--> Subscriber 3
```

Whenever a new video is uploaded, all subscribers receive notifications automatically.

The channel doesn't know anything about subscribers except that they are subscribed.

This is exactly how Observer Pattern works.

---

# Why Do We Need It?

## Bad Design

Imagine a blog application.

```java
public void publishPost(String post){

    emailService.send(post);

    smsService.send(post);

    pushNotification.send(post);

    auditService.log(post);
}
```

### Problems

Every time a new notification service is added:

```java
whatsappService.send(post);
```

You must modify existing code.

Again:

```java
slackService.send(post);
```

Modify again.

Again:

```java
teamsService.send(post);
```

Modify again.

This violates:

## Open Closed Principle (OCP)

> Software entities should be open for extension but closed for modification.

---

# Solution

Instead of directly calling services:

```text
Blog Publisher
      |
      +--> Observer
      +--> Observer
      +--> Observer
```

Publisher only updates observers.

Observers decide what to do.

---

# UML Diagram

```text
                +----------------+
                |    Subject     |
                +----------------+
                | addObserver()  |
                | removeObserver()|
                | updateObservers()|
                +--------+-------+
                         |
                         |
        ---------------------------------
        |               |               |
        v               v               v

  +------------+  +------------+  +------------+
  | Observer   |  | Observer   |  | Observer   |
  +------------+  +------------+  +------------+
  | update()   |  | update()   |  | update()   |
  +------------+  +------------+  +------------+
```

---

# Participants

## Subject (Publisher)

Maintains observer list.

Responsibilities:

* Register Observer
* Remove Observer
* Update Observers

Examples:

```text
BlogPublisher
PolicyService
StockMarket
WeatherStation
```

---

## Observer

Receives updates.

Examples:

```text
EmailNotification
SMSNotification
AuditNotification
DashboardNotification
```

---

# Implementation

## Observer Interface

```java
public interface Observer {

    void update(String message);
}
```

---

## Subject Interface

```java
public interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void updateObservers();
}
```

---

## Email Observer

```java
public class EmailSubscriber implements Observer {

    private String name;

    public EmailSubscriber(String name){
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

## Blog Publisher

```java
import java.util.ArrayList;
import java.util.List;

public class BlogPublisher implements Subject {

    private List<Observer> observers =
            new ArrayList<>();

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
    public void updateObservers() {

        for(Observer observer : observers){
            observer.update(latestPost);
        }
    }

    public void publishPost(String post){

        this.latestPost = post;

        System.out.println(
                "New Blog Published : " + post
        );

        updateObservers();
    }
}
```

---

## Client

```java
public class Main {

    public static void main(String[] args) {

        BlogPublisher blog =
                new BlogPublisher();

        Observer john =
                new EmailSubscriber("John");

        Observer david =
                new EmailSubscriber("David");

        blog.subscribe(john);
        blog.subscribe(david);

        blog.publishPost(
                "Observer Pattern Tutorial"
        );
    }
}
```

---

## Output

```text
New Blog Published : Observer Pattern Tutorial

John received: Observer Pattern Tutorial

David received: Observer Pattern Tutorial
```

---

# How It Works

```text
Step 1

John subscribes
David subscribes

Blog
 |
 +--> John
 |
 +--> David

---------------------

Step 2

New Blog Published

---------------------

Step 3

Observers Updated

---------------------

Blog -> John

Blog -> David

---------------------

All observers updated
```

---

# Production Example

## Insurance System

Bandhan Life Example

When policy gets approved:

```text
Policy Approved
```

Need:

```text
Send Email
Send SMS
Audit Logging
Update Dashboard
Notify Agent
```

Architecture:

```text
PolicyService
      |
      +--> EmailObserver
      +--> SmsObserver
      +--> AuditObserver
      +--> DashboardObserver
      +--> AgentObserver
```

Tomorrow:

```java
WhatsAppObserver
```

Add new observer.

No change in PolicyService.

---

# SOLID Principles

## Open Closed Principle (OCP)

Bad:

```java
if(email)
if(sms)
if(push)
if(whatsapp)
```

Observer:

```java
Add New Observer
```

No modification.

---

## Dependency Inversion Principle (DIP)

Bad:

```java
PolicyService
      |
      +--> EmailService
      +--> SMSService
```

Good:

```java
PolicyService
      |
      +--> Observer Interface
```

Depends on abstraction.

---

# Advantages

## Loose Coupling

Publisher doesn't know observer implementation.

---

## Easy Extension

New observers can be added easily.

---

## Reusable

Observers can be reused.

---

## Event-Driven Design

Perfect for notifications and events.

---

## Cleaner Code

No large if-else chains.

---

# Disadvantages

## Notification Storm

```text
1 Event
  |
1000 Observers
```

Can impact performance.

---

## Hard Debugging

Not obvious which observer executed.

---

## Memory Leaks

Forgotten observers remain registered.

---

## Execution Order Issues

Observer execution order may matter.

---

# Observer vs Pub/Sub

## Observer Pattern

```text
Publisher
    |
    +--> Subscriber
    +--> Subscriber
```

Publisher knows subscribers.

Usually used inside the same application.

Examples:

```text
Spring Events
Java Event Listeners
```

---

## Pub/Sub

```text
Publisher
     |
     v
   Kafka
     |
     +--> Consumer
     +--> Consumer
```

Publisher doesn't know consumers.

Consumers don't know publisher.

Used in distributed systems and microservices.

Examples:

```text
Kafka
RabbitMQ
AWS SNS
Redis Pub/Sub
```

---

# Spring Boot Example

Observer Pattern is heavily used in Spring.

## Event

```java
public class PolicyApprovedEvent {

    private Long policyId;

    public PolicyApprovedEvent(Long policyId) {
        this.policyId = policyId;
    }
}
```

---

## Publisher

```java
@Autowired
private ApplicationEventPublisher publisher;

publisher.publishEvent(
        new PolicyApprovedEvent(1001L)
);
```

---

## Observer

```java
@EventListener
public void sendEmail(
        PolicyApprovedEvent event) {

    System.out.println("Email Sent");
}
```

---

## Another Observer

```java
@EventListener
public void sendSMS(
        PolicyApprovedEvent event) {

    System.out.println("SMS Sent");
}
```

---

# Interview Questions

## What is Observer Pattern?

Observer Pattern is a behavioral design pattern that establishes a one-to-many relationship between a subject and observers. Whenever the subject changes state, all registered observers are updated automatically.

---

## When should you use Observer Pattern?

When multiple objects need to react to a change in another object.

Examples:

* Notification Systems
* Event Handling
* Stock Price Updates
* Weather Monitoring
* UI Event Listeners

---

## Which SOLID principles does it support?

* Open Closed Principle (OCP)
* Dependency Inversion Principle (DIP)

---

## Difference between Observer and Pub/Sub?

Observer works inside the same application and the publisher knows subscribers.

Pub/Sub uses a broker such as Kafka or RabbitMQ and provides complete decoupling.

---

## Real-world examples?

* Spring Application Events
* YouTube Subscribers
* Email Notifications
* Stock Market Updates
* Weather Stations

---

# Memory Tricks

## Trick 1

```text
ONE changes
    ↓
MANY update
```

Observer Pattern.

---

## Trick 2

```text
YouTube Channel
       ↓
Subscribers
```

Observer Pattern.

---

## Trick 3

```text
Policy Approved
       ↓
Email
SMS
Audit
Dashboard
```

Observer Pattern.

---

# Quick Revision

```text
Observer Pattern

Subject
   ↓
Observers
   ↓
update()

ONE → MANY

Subscribe → Update

Object → Object Communication

Examples:
- Event Listeners
- Notifications
- Spring Events
- UI Updates
- Stock Price Updates
- Weather Station Updates
```
