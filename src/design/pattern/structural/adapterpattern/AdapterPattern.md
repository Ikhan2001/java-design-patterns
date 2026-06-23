# Adapter Pattern

## Category

Structural Design Pattern

---

# Definition

The Adapter Pattern allows objects with incompatible interfaces to collaborate by creating an adapter that bridges the gap between them.

It acts as a translator between two systems that cannot communicate directly.

---

# One-Line Definition

```text
INCOMPATIBLE INTERFACES
          ↓
       ADAPTER
          ↓
WORK TOGETHER
```

or

```text
YOUR SYSTEM
      ↓
   ADAPTER
      ↓
THIRD PARTY SYSTEM
```

---

# Purpose

The Adapter Pattern allows classes with different interfaces to work together without modifying their source code.

---

# Common Use Cases

* Integrating third-party libraries
* Payment Gateway Integration
* Legacy System Migration
* SOAP to REST Migration
* Vendor API Integration
* DTO Conversion
* External Service Communication

---

# Real-Life Analogy

Imagine you travel from India to the USA.

Your charger:

```text
Indian Charger
```

The socket:

```text
US Socket
```

Problem:

```text
Charger Doesn't Fit
```

Solution:

```text
Travel Adapter
```

Visualization:

```text
Indian Charger
       ↓
Travel Adapter
       ↓
US Socket
```

The adapter makes incompatible systems work together.

---

# Problem Statement

Suppose your application already uses:

```java
public interface PaymentProcessor {

    void pay(double amount);
}
```

Application code:

```java
paymentProcessor.pay(1000);
```

Everything works.

---

Now Business Says:

```text
Integrate Razorpay
```

Razorpay provides:

```java
public class RazorpayGateway {

    public void makePayment(
            double amount) {

        System.out.println(
                "Payment Done Via Razorpay"
        );
    }
}
```

---

Problem:

Application expects:

```java
pay()
```

Razorpay provides:

```java
makePayment()
```

Interfaces are incompatible.

---

# Bad Solution

```java
if(provider.equals("RAZORPAY")) {

    razorpay.makePayment();
}
```

Later:

```text
Stripe

PayPal

Paytm
```

More if-else blocks.

Code becomes messy.

---

# Solution

Create:

```java
RazorpayAdapter
```

which converts:

```text
pay()
   ↓
makePayment()
```

---

# UML Diagram

```text
                 +-------------------+
                 | PaymentProcessor  |
                 +-------------------+
                 | pay()             |
                 +---------+---------+
                           ^
                           |
                    RazorpayAdapter
                           |
                           |
                           v

                  RazorpayGateway
                  makePayment()
```

---

# Components

## Target

Expected interface.

```java
PaymentProcessor
```

---

## Adaptee

Existing incompatible class.

```java
RazorpayGateway
```

---

## Adapter

Bridge between both.

```java
RazorpayAdapter
```

---

## Client

Uses target interface.

```java
PaymentProcessor
```

---

# Working Example

## Target Interface

```java
public interface PaymentProcessor {

    void pay(double amount);
}
```

---

## Adaptee

```java
public class RazorpayGateway {

    public void makePayment(
            double amount) {

        System.out.println(
                "Payment Done Via Razorpay : "
                + amount
        );
    }
}
```

---

## Adapter

```java
public class RazorpayAdapter
        implements PaymentProcessor {

    private RazorpayGateway gateway;

    public RazorpayAdapter(
            RazorpayGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public void pay(
            double amount) {

        gateway.makePayment(
                amount
        );
    }
}
```

---

## Client

```java
public class AdapterExample {

    public static void main(
            String[] args) {

        PaymentProcessor processor =
                new RazorpayAdapter(
                        new RazorpayGateway()
                );

        processor.pay(5000);
    }
}
```

---

# Output

```text
Payment Done Via Razorpay : 5000
```

---

# Execution Flow

Application calls:

```java
processor.pay(5000);
```

Adapter converts:

```java
gateway.makePayment(5000);
```

Visualization:

```text
Application
      ↓
pay(5000)
      ↓
RazorpayAdapter
      ↓
makePayment(5000)
      ↓
RazorpayGateway
```

---

# Production Story

Suppose your Insurance Application currently uses:

```text
Internal Payment Gateway
```

Business says:

```text
Add Razorpay

Add Stripe

Add PayPal
```

Instead of modifying existing code:

Create:

```java
RazorpayAdapter

StripeAdapter

PaypalAdapter
```

Application still uses:

```java
paymentProcessor.pay();
```

No client code changes.

---

# Spring Boot Example #1

## Payment Gateway Integration

Application Interface:

```java
public interface PaymentProcessor {

    void pay(double amount);
}
```

Third Party:

```java
StripeClient
```

Adapter:

```java
StripeAdapter
```

Client remains unaware of Stripe implementation.

---

# Spring Boot Example #2

## Legacy System Migration

Old System:

```java
LegacyCustomerService
```

New System:

```java
CustomerService
```

Adapter:

```java
LegacyCustomerAdapter
```

Used during migration projects.

---

# Spring Boot Example #3

## Vendor API Integration

Application requires:

```text
KYC Verification
```

Third Party Vendors:

```text
Vendor A

Vendor B

Government API
```

Each provides different response structures.

Adapter converts:

```text
Vendor Response
      ↓
Adapter
      ↓
Internal DTO
```

Application uses a common structure.

---

# Spring Boot Example #4

## SOAP to REST Migration

Old System:

```text
SOAP Service
```

New System:

```text
REST API
```

Adapter converts:

```text
SOAP Response
      ↓
Adapter
      ↓
REST DTO
```

Useful in enterprise modernization projects.

---

# Microservice Example

Suppose:

```text
Claim Service
```

expects:

```java
CustomerDTO
```

External Vendor returns:

```java
CustomerResponse
```

Adapter converts:

```java
CustomerResponse
      ↓
CustomerAdapter
      ↓
CustomerDTO
```

Internal services remain unchanged.

---

# Object Adapter vs Class Adapter

## Object Adapter (Most Common)

Uses Composition.

```java
private RazorpayGateway gateway;
```

Benefits:

```text
Flexible

Reusable

Preferred Approach
```

---

## Class Adapter

Uses Inheritance.

```java
class Adapter
    extends RazorpayGateway
    implements PaymentProcessor
```

Benefits:

```text
Direct Adaptation
```

Limitation:

```text
Single Inheritance Restriction
```

---

# Adapter vs Decorator

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

---

## Decorator

Purpose:

```text
ADD BEHAVIOR
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

# Adapter vs Facade

## Adapter

Purpose:

```text
Convert Interface
```

Example:

```text
PayPal API
      ↓
Adapter
      ↓
PaymentProcessor
```

---

## Facade

Purpose:

```text
Simplify Interface
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

## Reuse Existing Code

No modification required in third-party libraries.

---

## Open Closed Principle

Add new adapters without changing existing code.

---

## Cleaner Integration

External systems remain isolated.

---

## Better Maintainability

All integration logic stays in one place.

---

# Disadvantages

## Additional Classes

Each integration may require a new adapter.

---

## Extra Layer

Adds abstraction.

---

## Complexity

Too many adapters can increase maintenance effort.

---

# Most Important Spring Boot Use Cases

```text
Payment Gateway Integration

Legacy System Migration

Vendor API Integration

SOAP to REST Migration

DTO Conversion

External Service Integration
```

---

# Interview Questions

## What is Adapter Pattern?

Adapter Pattern allows incompatible interfaces to work together by acting as a bridge between them.

---

## When should you use it?

When:

```text
Third Party APIs

Legacy Systems

Different Interfaces

Vendor Integrations

Payment Gateways
```

must work with your application.

---

## Which SOLID Principle Does It Support?

```text
Open Closed Principle
```

New adapters can be added without modifying client code.

---

# Interview Gold Answer

## Where Have You Used Adapter Pattern?

```text
1. Payment Gateway Integration
   (Razorpay, Stripe, PayPal)

2. Legacy System Migration

3. Vendor API Integration

4. SOAP to REST Migration

5. DTO Conversion

6. External Service Communication
```

---

# Memory Tricks

## Trick 1

```text
Indian Charger
      ↓
Travel Adapter
      ↓
US Socket
```

---

## Trick 2

```text
English Speaker
      ↓
Translator
      ↓
Japanese Speaker
```

Adapter = Translator.

---

## Trick 3

```text
Different Interfaces
          ↓
       Adapter
          ↓
      Compatible
```

---

# Quick Revision

```text
Adapter Pattern

Purpose:
Make Incompatible Interfaces Compatible

Components:
Target
Adapter
Adaptee
Client

Examples:
Payment Gateway
Vendor API
Legacy Systems
DTO Conversion

Benefits:
Compatibility
Reusability
Open Closed Principle
```

---

# Ultimate Formula

```text
Decorator
ADD BEHAVIOR

Facade
SIMPLIFY COMPLEXITY

Proxy
CONTROL ACCESS

Adapter
MAKE COMPATIBLE
```

## Visual Memory

```text
Your System
      ↓
Adapter
      ↓
Third Party System
```

The moment you hear:

```text
Third Party API

Legacy System

Different Interface

Payment Gateway

Vendor Integration
```

Think **Adapter Pattern**.
