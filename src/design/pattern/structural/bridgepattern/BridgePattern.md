# Bridge Pattern

## Category

Structural Design Pattern

---

# Definition

The Bridge Pattern separates an abstraction from its implementation so that both can vary independently.

Instead of creating subclasses for every possible combination, Bridge connects two independent hierarchies using composition.

---

# One-Line Definition

```text
ABSTRACTION
      ↓
    BRIDGE
      ↓
IMPLEMENTATION
```

or

```text
SEPARATE
WHAT YOU DO
      FROM
HOW YOU DO IT
```

---

# Purpose

The Bridge Pattern decouples an abstraction from its implementation so that both can evolve independently.

It helps avoid class explosion when multiple dimensions vary independently.

---

# Common Use Cases

* Notification Types + Notification Channels
* Payment Types + Payment Gateways
* Reports + Storage Providers
* Documents + Storage Systems
* UI Components + Themes
* Cross Platform Applications

---

# Real-Life Analogy

Think about a TV Remote.

Remote Types:

```text
Basic Remote

Advanced Remote
```

TV Brands:

```text
Sony TV

Samsung TV

LG TV
```

Without Bridge:

```text
SonyBasicRemote

SonyAdvancedRemote

SamsungBasicRemote

SamsungAdvancedRemote

LGBasicRemote

LGAdvancedRemote
```

Too many classes.

With Bridge:

```text
Remote
   ↓
TV
```

Any Remote can work with any TV.

---

# Problem Statement

Suppose we are building a Notification System.

Notification Types:

```text
Alert Notification

Reminder Notification

Marketing Notification
```

Notification Channels:

```text
Email

SMS

WhatsApp
```

---

# Bad Design

Create classes for every combination:

```text
AlertEmailNotification

AlertSmsNotification

AlertWhatsAppNotification

ReminderEmailNotification

ReminderSmsNotification

ReminderWhatsAppNotification

MarketingEmailNotification

MarketingSmsNotification

MarketingWhatsAppNotification
```

Total:

```text
3 × 3 = 9 Classes
```

---

Now add:

```text
Push Notification
```

Total:

```text
3 × 4 = 12 Classes
```

---

Add:

```text
Promotion Notification
```

Total:

```text
4 × 4 = 16 Classes
```

Class Explosion.

---

# Solution

Separate:

## Abstraction

```text
Notification
```

and

## Implementation

```text
MessageSender
```

Visualization:

```text
Notification
      ↓
      HAS A
      ↓
MessageSender
```

Now both can vary independently.

---

# UML Diagram

```text
                 Notification
                       |
         ----------------------------
         |             |            |
         v             v            v

      Alert       Reminder     Marketing

                       |
                       |
                       v

                 MessageSender
                       |
        --------------------------------
        |              |               |
        v              v               v

      Email         SMS          WhatsApp
```

---

# Components

## Implementor

Low-level implementation interface.

```java
MessageSender
```

---

## Concrete Implementors

```java
EmailSender

SmsSender

WhatsAppSender
```

---

## Abstraction

```java
Notification
```

---

## Refined Abstractions

```java
AlertNotification

ReminderNotification

MarketingNotification
```

---

# Working Example

## Implementor

```java
public interface MessageSender {

    void sendMessage(
            String message
    );
}
```

---

## Email Sender

```java
public class EmailSender
        implements MessageSender {

    @Override
    public void sendMessage(
            String message) {

        System.out.println(
                "Email : "
                + message
        );
    }
}
```

---

## SMS Sender

```java
public class SmsSender
        implements MessageSender {

    @Override
    public void sendMessage(
            String message) {

        System.out.println(
                "SMS : "
                + message
        );
    }
}
```

---

## WhatsApp Sender

```java
public class WhatsAppSender
        implements MessageSender {

    @Override
    public void sendMessage(
            String message) {

        System.out.println(
                "WhatsApp : "
                + message
        );
    }
}
```

---

## Abstraction

```java
public abstract class Notification {

    protected MessageSender sender;

    public Notification(
            MessageSender sender) {

        this.sender = sender;
    }

    public abstract void notifyUser(
            String message
    );
}
```

---

## Alert Notification

```java
public class AlertNotification
        extends Notification {

    public AlertNotification(
            MessageSender sender) {

        super(sender);
    }

    @Override
    public void notifyUser(
            String message) {

        sender.sendMessage(
                "[ALERT] "
                + message
        );
    }
}
```

---

## Reminder Notification

```java
public class ReminderNotification
        extends Notification {

    public ReminderNotification(
            MessageSender sender) {

        super(sender);
    }

    @Override
    public void notifyUser(
            String message) {

        sender.sendMessage(
                "[REMINDER] "
                + message
        );
    }
}
```

---

## Client

```java
public class BridgeExample {

    public static void main(
            String[] args) {

        MessageSender sender =
                new EmailSender();

        Notification notification =
                new AlertNotification(
                        sender
                );

        notification.notifyUser(
                "Premium Due"
        );
    }
}
```

---

# Output

```text
Email : [ALERT] Premium Due
```

---

# Execution Flow

Object Creation:

```java
Notification notification =
        new AlertNotification(
                new SmsSender()
        );
```

Visualization:

```text
AlertNotification
        ↓
     HAS A
        ↓
SmsSender
```

Execution:

```java
notification.notifyUser(
        "Premium Due"
);
```

Flow:

```text
AlertNotification
        ↓
SmsSender
        ↓
SMS Sent
```

---

# Why Bridge?

Without Bridge:

```text
AlertEmailNotification

AlertSmsNotification

AlertWhatsAppNotification

ReminderEmailNotification

ReminderSmsNotification

ReminderWhatsAppNotification
```

Too many classes.

---

With Bridge:

```text
Notification
      ↓
MessageSender
```

Only:

```text
3 Notification Types

3 Sender Types
```

instead of:

```text
9 Combination Classes
```

---

# Production Example #1

## Notification Service

Notification Types:

```text
Policy Alert

Claim Alert

Payment Reminder
```

Channels:

```text
Email

SMS

WhatsApp
```

Bridge fits perfectly.

---

# Production Example #2

## Report Generation System

Report Types:

```text
PDF Report

Excel Report

CSV Report
```

Storage Providers:

```text
S3

Database

FTP
```

Without Bridge:

```text
PdfS3Report

PdfDatabaseReport

PdfFtpReport

ExcelS3Report

ExcelDatabaseReport
```

Huge hierarchy.

Bridge solves this.

---

# Production Example #3

## Payment System

Payment Types:

```text
Credit Card

UPI

Net Banking
```

Gateways:

```text
Razorpay

Stripe

PayPal
```

Bridge Structure:

```text
Payment
     ↓
Gateway
```

Both vary independently.

---

# Spring Boot Example

## Notification Service

```java
Notification notification =
        new ReminderNotification(
                new WhatsAppSender()
        );
```

Tomorrow business says:

```text
Add Telegram
```

Create:

```java
TelegramSender
```

No changes required in:

```java
Notification

AlertNotification

ReminderNotification
```

---

# Microservice Example

Suppose Notification Service supports:

```text
Email

SMS

WhatsApp
```

New Requirement:

```text
Telegram
```

Just add:

```java
TelegramSender
```

Everything else remains unchanged.

---

# Bridge vs Adapter

## Adapter

Purpose:

```text
MAKE COMPATIBLE
```

Example:

```text
pay()
 ↓
makePayment()
```

Used when interfaces already exist and don't match.

---

## Bridge

Purpose:

```text
SEPARATE ABSTRACTION
FROM IMPLEMENTATION
```

Example:

```text
Notification
      ↓
Sender
```

Used during system design.

---

# Bridge vs Strategy

## Bridge

Solves:

```text
Structure Problem
```

Two hierarchies.

---

## Strategy

Solves:

```text
Algorithm Problem
```

Multiple algorithms.

---

# Advantages

## Avoids Class Explosion

Most important benefit.

---

## Open Closed Principle

Add new abstractions or implementations independently.

---

## Better Maintainability

Reduced coupling.

---

## High Scalability

Easy to extend.

---

# Disadvantages

## More Classes

Adds extra abstraction layers.

---

## Slightly Complex Design

Can be overkill for small systems.

---

# Most Important Spring Boot Use Cases

```text
Notification Types + Channels

Payment Types + Gateways

Reports + Storage Providers

Documents + Storage Systems

UI Components + Themes
```

---

# Interview Questions

## What is Bridge Pattern?

Bridge Pattern separates abstraction from implementation so both can change independently.

---

## When should you use it?

When:

```text
Two Dimensions
Change Independently
```

and subclass combinations become too large.

---

## Which SOLID Principle Does It Support?

```text
Open Closed Principle
```

New abstractions and implementations can be added independently.

---

# Interview Gold Answer

## Where Have You Used Bridge Pattern?

```text
1. Notification Types + Channels

2. Payment Types + Gateways

3. Reports + Storage Providers

4. Documents + Storage Systems

5. UI Components + Themes

6. Cross Platform Applications
```

---

# Memory Tricks

## Trick 1

```text
Two Axes Changing
         ↓
Bridge
```

---

## Trick 2

```text
Notification
      ↓
Sender
```

Separate both.

---

## Trick 3

```text
3 Types
×
3 Channels

=
9 Classes

Bridge Saves Us
```

---

# Quick Revision

```text
Bridge Pattern

Purpose:
Separate Abstraction From Implementation

Components:
Abstraction
Refined Abstraction
Implementor
Concrete Implementor

Examples:
Notification + Sender
Payment + Gateway
Reports + Storage

Benefits:
Avoid Class Explosion
Scalable
Maintainable
Flexible
```

---

# Ultimate Formula

```text
Adapter
MAKE COMPATIBLE

Decorator
ADD BEHAVIOR

Facade
SIMPLIFY COMPLEXITY

Proxy
CONTROL ACCESS

Bridge
SEPARATE TWO DIMENSIONS
```

## Visual Memory

```text
Notification
      ↓
MessageSender
```

When you hear:

```text
Class Explosion

Two Independent Hierarchies

Abstraction + Implementation
```

Think **Bridge Pattern**.
