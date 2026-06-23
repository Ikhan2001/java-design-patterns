# Decorator Pattern

## Category

Structural Design Pattern

---

# Definition

The Decorator Pattern allows behavior to be added to an object dynamically without modifying its original class.

In simple words:

```text
OBJECT
   ↓
WRAP
   ↓
ENHANCE
```

or

```text
Existing Object
      ↓
Add Features Dynamically
```

---

# Real-Life Analogy

Think of a Notification System.

Initially:

```text
Send Email
```

Later requirements:

```text
Send Email + SMS
```

Then:

```text
Send Email + SMS + WhatsApp
```

Then:

```text
Send Email + SMS + WhatsApp + Push Notification
```

Instead of creating multiple subclasses, we wrap the notification object with decorators.

---

# Problem Statement

Suppose an Insurance Application sends notifications when a policy is approved.

Basic requirement:

```text
Email Notification
```

Future requirements:

```text
Email + SMS

Email + SMS + WhatsApp

Email + SMS + WhatsApp + Push Notification
```

---

# Bad Design (Inheritance Explosion)

```text
EmailNotification

EmailSmsNotification

EmailSmsWhatsAppNotification

EmailSmsWhatsAppPushNotification
```

As new channels are added:

```text
Telegram
Slack
Teams
```

More subclasses are required.

This leads to:

```text
Class Explosion
```

---

# Problems

* Too many subclasses
* Difficult maintenance
* Rigid design
* Hard to extend

---

# Solution

Wrap the object dynamically.

```text
Email
  ↓
SMS
  ↓
WhatsApp
  ↓
Push Notification
```

Each decorator adds new behavior.

---

# UML Diagram

```text
                 +-------------------+
                 |   Notification    |
                 +-------------------+
                 | send()            |
                 +---------+---------+
                           ^
                           |
                ---------------------
                |                   |
                v                   v

      EmailNotification   NotificationDecorator
                                   ^
                                   |
                    -----------------------------
                    |             |             |
                    v             v             v

             SmsDecorator WhatsAppDecorator PushDecorator
```

---

# Components

## Component

Common contract.

```java
Notification
```

---

## Concrete Component

Original object.

```java
EmailNotification
```

---

## Decorator

Wrapper class.

```java
NotificationDecorator
```

---

## Concrete Decorators

Additional behaviors.

```java
SmsDecorator

WhatsAppDecorator

PushNotificationDecorator
```

---

# Working Example

## Component

```java
public interface Notification {

    void send();
}
```

---

## Concrete Component

```java
public class EmailNotification
        implements Notification {

    @Override
    public void send() {

        System.out.println(
                "Sending Email Notification"
        );
    }
}
```

---

## Abstract Decorator

```java
public abstract class NotificationDecorator
        implements Notification {

    protected Notification notification;

    public NotificationDecorator(
            Notification notification) {

        this.notification = notification;
    }
}
```

---

## SMS Decorator

```java
public class SmsDecorator
        extends NotificationDecorator {

    public SmsDecorator(
            Notification notification) {

        super(notification);
    }

    @Override
    public void send() {

        notification.send();

        System.out.println(
                "Sending SMS Notification"
        );
    }
}
```

---

## WhatsApp Decorator

```java
public class WhatsAppDecorator
        extends NotificationDecorator {

    public WhatsAppDecorator(
            Notification notification) {

        super(notification);
    }

    @Override
    public void send() {

        notification.send();

        System.out.println(
                "Sending WhatsApp Notification"
        );
    }
}
```

---

## Push Notification Decorator

```java
public class PushNotificationDecorator
        extends NotificationDecorator {

    public PushNotificationDecorator(
            Notification notification) {

        super(notification);
    }

    @Override
    public void send() {

        notification.send();

        System.out.println(
                "Sending Push Notification"
        );
    }
}
```

---

## Client

```java
public class DecoratorExample {

    public static void main(String[] args) {

        Notification notification =
                new EmailNotification();

        notification =
                new SmsDecorator(notification);

        notification =
                new WhatsAppDecorator(notification);

        notification =
                new PushNotificationDecorator(notification);

        notification.send();
    }
}
```

---

# Output

```text
Sending Email Notification

Sending SMS Notification

Sending WhatsApp Notification

Sending Push Notification
```

---

# Execution Flow

## Object Creation

```java
Notification notification =
        new EmailNotification();

notification =
        new SmsDecorator(notification);

notification =
        new WhatsAppDecorator(notification);

notification =
        new PushNotificationDecorator(notification);
```

Memory Structure:

```text
PushDecorator
      |
      v

WhatsAppDecorator
      |
      v

SmsDecorator
      |
      v

EmailNotification
```

---

# Method Call Flow

```text
PushDecorator.send()
        |
        v

WhatsAppDecorator.send()
        |
        v

SmsDecorator.send()
        |
        v

EmailNotification.send()
```

Then execution returns back:

```text
Email Notification

SMS Notification

WhatsApp Notification

Push Notification
```

---

# Why super(notification)?

```java
public SmsDecorator(
        Notification notification) {

    super(notification);
}
```

The parent class stores the wrapped object.

```java
protected Notification notification;
```

This creates:

```text
SmsDecorator
      |
      +------> EmailNotification
```

Decorator wraps the previous object using composition.

---

# Production Example

## Insurance Notification System

Policy Approved:

```text
Policy Approved Event
```

Basic User:

```text
Email
```

Premium User:

```text
Email
+
SMS
```

VIP User:

```text
Email
+
SMS
+
WhatsApp
```

Mobile App User:

```text
Email
+
SMS
+
WhatsApp
+
Push Notification
```

Decorator allows these combinations without creating new subclasses.

---

# Java Real World Example

Java IO Streams use Decorator Pattern.

```java
InputStream stream =
    new DataInputStream(
        new BufferedInputStream(
            new FileInputStream("file.txt")
        )
    );
```

Visualization:

```text
FileInputStream
       ↓
BufferedInputStream
       ↓
DataInputStream
```

Each wrapper adds new functionality.

---

# Decorator vs Inheritance

| Decorator                 | Inheritance             |
| ------------------------- | ----------------------- |
| Runtime Behavior Addition | Compile-Time Behavior   |
| Uses Composition          | Uses Inheritance        |
| Flexible                  | Rigid                   |
| Avoids Class Explosion    | Creates Many Subclasses |

---

# Decorator vs Adapter

## Adapter

```text
Convert Interface
```

Example:

```text
USB-C → HDMI
```

---

## Decorator

```text
Add New Behavior
```

Example:

```text
Email
 ↓
SMS
 ↓
WhatsApp
```

---

# Advantages

## Open Closed Principle

Add new decorators without modifying existing code.

---

## Runtime Flexibility

Behavior can be added dynamically.

---

## Avoid Class Explosion

No need for dozens of subclasses.

---

## Reusable

Decorators can be combined in different ways.

---

## Single Responsibility

Each decorator adds one responsibility.

---

# Disadvantages

## Many Small Classes

Can create multiple decorator classes.

---

## Debugging Complexity

Multiple wrapper layers can be harder to trace.

---

## Extra Objects

Each decorator creates a new wrapper object.

---

# Interview Questions

## What is Decorator Pattern?

Decorator Pattern dynamically adds responsibilities to an object by wrapping it with decorator objects without modifying the original class.

---

## When should you use it?

When:

```text
Need Dynamic Features

Avoid Subclass Explosion

Enhance Existing Object

Add Behavior At Runtime
```

---

## Which SOLID Principles Does It Support?

```text
Open Closed Principle

Single Responsibility Principle
```

---

## Real World Examples

```text
Notification System

Java IO Streams

Spring Security

Compression & Encryption Wrappers

Logging Wrappers
```

---

# Memory Tricks

## Trick 1

```text
OBJECT
 ↓
WRAP
 ↓
ENHANCE
```

---

## Trick 2

```text
Email
 ↓
SMS
 ↓
WhatsApp
 ↓
Push
```

Behavior keeps growing.

---

## Trick 3

```text
Need New Feature?
      ↓
Wrap Object
```

Decorator Pattern.

---

# Quick Revision

```text
Decorator Pattern

Object
  ↓
Wrap
  ↓
Enhance

Components:

Component
Concrete Component
Decorator
Concrete Decorators

Examples:

Notification System
Java IO Streams
Spring Security

Benefits:

Runtime Flexibility
Avoid Class Explosion
Open Closed Principle
```

---

# Ultimate Memory Formula

```text
Observer
ONE → MANY

Strategy
ONE TASK → MANY ALGORITHMS

Template Method
SAME PROCESS
↓
STEP CHANGES

Command
ACTION
↓
OBJECT

Chain Of Responsibility
REQUEST
↓
PASS UNTIL HANDLED

Decorator
OBJECT
↓
WRAP
↓
ENHANCE
```
