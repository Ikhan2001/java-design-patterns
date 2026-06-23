# Facade Pattern

## Category

Structural Design Pattern

---

# Definition

The Facade Pattern provides a simplified interface to a complex subsystem by hiding internal complexity behind a single entry point.

Instead of interacting with multiple services directly, the client interacts with a single Facade class.

---

# One-Line Definition

```text
COMPLEX SYSTEM
      ↓
   FACADE
      ↓
SIMPLE INTERFACE
```

or

```text
Hide Complexity
      ↓
Expose Simplicity
```

---

# Purpose

The Facade Pattern provides a simplified interface to a complex subsystem, making it easier to interact with by hiding the internal complexity.

---

# Common Use Cases

* Simplifying complex libraries or frameworks.
* Wrapping legacy code with a simple API.
* Service orchestration in microservices.
* Hiding complex workflows behind a single method.
* Providing a cleaner interface for clients.

---

# Real-Life Analogy

Think about ordering food from Swiggy.

You don't call:

```text
Restaurant

Delivery Partner

Payment Gateway

Invoice Service
```

individually.

You simply:

```text
Place Order
```

Swiggy internally coordinates everything.

Swiggy acts as the Facade.

---

# Problem Statement

Suppose we are building an E-Commerce Application.

When a customer places an order:

```text
1. Check Inventory

2. Process Payment

3. Create Shipment

4. Generate Invoice

5. Send Notification
```

Without Facade:

```java
inventoryService.checkStock();

paymentService.processPayment();

shippingService.shipOrder();

notificationService.sendNotification();
```

Client must know every service.

---

# Problems

* Too many dependencies
* Complex client code
* Tight coupling
* Hard maintenance
* Difficult onboarding for new developers

---

# Solution

Create a Facade:

```java
OrderProcessingFacade
```

Client only calls:

```java
facade.placeOrder();
```

Facade handles everything internally.

---

# UML Diagram

```text
                    Client
                       |
                       v

             OrderProcessingFacade
                       |
     -----------------------------------
     |          |          |           |
     v          v          v           v

 Inventory   Payment   Shipping   Notification
```

---

# Components

## Facade

Provides simplified API.

```java
OrderProcessingFacade
```

---

## Subsystems

Complex internal services.

```java
InventoryService

PaymentService

ShippingService

NotificationService
```

---

## Client

Uses only Facade.

```java
facade.placeOrder();
```

---

# Working Example

## Inventory Service

```java
public class InventoryService {

    public void checkStock() {

        System.out.println(
                "Inventory Checked"
        );
    }
}
```

---

## Payment Service

```java
public class PaymentService {

    public void processPayment() {

        System.out.println(
                "Payment Processed"
        );
    }
}
```

---

## Shipping Service

```java
public class ShippingService {

    public void shipOrder() {

        System.out.println(
                "Order Shipped"
        );
    }
}
```

---

## Notification Service

```java
public class NotificationService {

    public void sendNotification() {

        System.out.println(
                "Notification Sent"
        );
    }
}
```

---

## Facade

```java
public class OrderProcessingFacade {

    private final InventoryService inventoryService =
            new InventoryService();

    private final PaymentService paymentService =
            new PaymentService();

    private final ShippingService shippingService =
            new ShippingService();

    private final NotificationService notificationService =
            new NotificationService();

    public void placeOrder() {

        inventoryService.checkStock();

        paymentService.processPayment();

        shippingService.shipOrder();

        notificationService.sendNotification();
    }
}
```

---

## Client

```java
public class FacadeExample {

    public static void main(String[] args) {

        OrderProcessingFacade facade =
                new OrderProcessingFacade();

        facade.placeOrder();
    }
}
```

---

# Output

```text
Inventory Checked

Payment Processed

Order Shipped

Notification Sent
```

---

# Execution Flow

Without Facade:

```text
Client
  |
  +--> Inventory Service

  +--> Payment Service

  +--> Shipping Service

  +--> Notification Service
```

Client knows everything.

---

With Facade:

```text
Client
   |
   v

OrderProcessingFacade
   |
   +--> Inventory

   +--> Payment

   +--> Shipping

   +--> Notification
```

Client knows only one class.

---

# Spring Boot Example

## User Registration

Without Facade:

```java
userService.save();

profileService.create();

emailService.send();

tokenService.generate();
```

Controller becomes messy.

---

With Facade:

```java
userFacade.registerUser();
```

Internally:

```text
Save User

Create Profile

Generate Token

Send Welcome Email
```

Controller stays clean.

---

# Microservice Example

Order Service receives:

```text
Create Order
```

Need to call:

```text
Inventory Service

Payment Service

Shipping Service

Notification Service
```

Facade:

```java
orderFacade.placeOrder();
```

Internally orchestrates all services.

Very common in microservice architectures.

---

# Insurance Domain Example (Bandhan Life)

When issuing a policy:

Without Facade:

```java
validationService.validate();

premiumService.calculate();

paymentService.process();

documentService.generate();

notificationService.send();
```

With Facade:

```java
policyFacade.issuePolicy();
```

Internally:

```text
Validate Policy

Calculate Premium

Generate Documents

Store Documents

Send Notifications
```

Single API call.

Multiple operations.

---

# Spring Boot & Microservices Use Cases

## Order Processing

```java
orderFacade.placeOrder();
```

Internally:

```text
Inventory Check

Payment

Shipping

Notification
```

---

## User Registration

```java
userFacade.registerUser();
```

Internally:

```text
Save User

Create Profile

Generate Token

Send Email
```

---

## Policy Issuance

```java
policyFacade.issuePolicy();
```

Internally:

```text
Validation

Premium Calculation

Document Generation

Notification
```

---

## Payment Processing

```java
paymentFacade.processPayment();
```

Internally:

```text
Fraud Check

Balance Validation

Payment Gateway

Notification
```

---

# Facade vs Adapter

## Facade

```text
Simplifies Interface
```

Example:

```text
Inventory
Payment
Shipping

↓

OrderFacade
```

---

## Adapter

```text
Makes Incompatible Interfaces Compatible
```

Example:

```text
Third Party Payment API

↓

PaymentGateway Interface
```

---

# Facade vs Decorator

## Decorator

```text
Add Behavior
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

## Facade

```text
Simplify Access
```

Example:

```text
Inventory
Payment
Shipping

↓

OrderFacade
```

---

# Advantages

## Reduces Complexity

Client sees only a simple API.

---

## Loose Coupling

Client does not depend on subsystem details.

---

## Cleaner Code

Single entry point.

---

## Easier Maintenance

Subsystem changes do not affect client.

---

## Better Readability

One method replaces many service calls.

---

# Disadvantages

## Can Become a God Class

If too many responsibilities are added.

---

## May Hide Advanced Features

Some subsystem capabilities become inaccessible.

---

## Additional Abstraction Layer

Can be unnecessary for very small systems.

---

# Interview Questions

## What is Facade Pattern?

Facade Pattern provides a simplified interface to a complex subsystem and hides internal complexity from clients.

---

## When should you use it?

When:

```text
System Is Complex

Too Many Dependencies

Need Cleaner Client Code

Need Single Entry Point
```

---

## Which SOLID Principles Does It Support?

```text
Single Responsibility Principle

Dependency Inversion Principle
```

---

## Real World Examples

```text
Order Processing System

User Registration

Policy Issuance

Payment Processing

Home Theater System

Banking Transactions
```

---

# Memory Tricks

## Trick 1

```text
ONE BUTTON
      ↓
MANY OPERATIONS
```

Facade Pattern.

---

## Trick 2

```text
Inventory
Payment
Shipping
Notification

      ↓

OrderFacade
```

One API.

Many services.

---

## Trick 3

```text
Too Much Complexity?
        ↓
Create Facade
```

---

# Quick Revision

```text
Facade Pattern

Complex System
      ↓
Facade
      ↓
Simple Interface

Components:

Facade
Subsystems
Client

Examples:

Order Processing
User Registration
Policy Issuance
Payment Processing

Benefits:

Simplifies Complexity
Loose Coupling
Cleaner Code
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

Facade
COMPLEX SYSTEM
↓
SIMPLE INTERFACE
```
