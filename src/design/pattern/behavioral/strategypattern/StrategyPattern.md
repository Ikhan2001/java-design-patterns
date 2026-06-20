# Strategy Pattern

## Category

Behavioral Design Pattern

---

# Definition

The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.

It allows the algorithm to vary independently from the client that uses it.

In simple words:

```text id="ylg7e4"
ONE TASK
    ↓
MANY WAYS
    ↓
CHOOSE ONE AT RUNTIME
```

---

# Real-Life Analogy

Think about Google Maps.

You want to travel from Delhi to Noida.

Google Maps can provide:

```text id="tzebll"
Car Route
Walking Route
Bike Route
Metro Route
```

Destination remains the same.

Only the route selection strategy changes.

This is Strategy Pattern.

---

# Why Do We Need It?

## Bad Design

Suppose we are building a payment system.

```java id="9s7dx4"
public class PaymentService {

    public void pay(String paymentType,
                    double amount){

        if(paymentType.equals("CARD")){
            System.out.println(
                "Credit Card Payment"
            );
        }
        else if(paymentType.equals("UPI")){
            System.out.println(
                "UPI Payment"
            );
        }
        else if(paymentType.equals("PAYPAL")){
            System.out.println(
                "PayPal Payment"
            );
        }
    }
}
```

---

# Problems

Adding a new payment method:

```text id="vnkrq2"
Google Pay
Apple Pay
Amazon Pay
Crypto
```

Requires modifying existing code.

Again:

```java id="ydrd4p"
else if(...)
```

This creates:

* Large if-else chains
* Difficult maintenance
* Tight coupling

---

# Violates Open Closed Principle

```text id="lzbl87"
Open for Extension ❌
Closed for Modification ❌
```

Because every new payment method changes existing code.

---

# Solution

Move each algorithm into a separate class.

```text id="jmyhys"
Payment Strategy
      |
      +--> Credit Card
      +--> PayPal
      +--> UPI
      +--> Google Pay
```

Now the client depends only on the strategy interface.

---

# UML Diagram

```text id="l0h0gr"
                +----------------------+
                |   PaymentStrategy    |
                +----------------------+
                | + pay(amount)        |
                +----------+-----------+
                           |
     --------------------------------------------
     |                    |                     |
     v                    v                     v

+-------------+   +-------------+   +-------------+
| CreditCard  |   |   PayPal    |   |     UPI     |
+-------------+   +-------------+   +-------------+

                           ^
                           |
                  +------------------+
                  | PaymentService   |
                  +------------------+
                  | strategy         |
                  +------------------+
```

---

# Components

## Strategy

Common interface.

```java id="ppv1aj"
PaymentStrategy
```

---

## Concrete Strategies

Different algorithms.

```java id="i3qmf8"
CreditCardPayment
PayPalPayment
UPIPayment
GooglePayPayment
```

---

## Context

Uses strategy.

```java id="0x5zbw"
PaymentService
```

---

# Implementation

## Strategy Interface

```java id="2i0tv0"
public interface PaymentStrategy {

    void pay(double amount);
}
```

---

## Credit Card Strategy

```java id="t0rljq"
public class CreditCardPayment
        implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Paid ₹" + amount +
            " using Credit Card"
        );
    }
}
```

---

## PayPal Strategy

```java id="iwu4it"
public class PayPalPayment
        implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Paid ₹" + amount +
            " using PayPal"
        );
    }
}
```

---

## UPI Strategy

```java id="4fqky6"
public class UPIPayment
        implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Paid ₹" + amount +
            " using UPI"
        );
    }
}
```

---

## Context

```java id="g0vnsd"
public class PaymentService {

    private PaymentStrategy strategy;

    public PaymentService(
            PaymentStrategy strategy) {

        this.strategy = strategy;
    }

    public void processPayment(
            double amount) {

        strategy.pay(amount);
    }
}
```

---

## Client

```java id="e0nszx"
public class StrategyExample {

    public static void main(String[] args) {

        PaymentService paymentService =
            new PaymentService(
                new CreditCardPayment());

        paymentService.processPayment(
                1000);

        paymentService =
            new PaymentService(
                new UPIPayment());

        paymentService.processPayment(
                500);
    }
}
```

---

# Output

```text id="hlm6c8"
Paid ₹1000 using Credit Card

Paid ₹500 using UPI
```

---

# How It Works

```text id="6v4nnm"
Client
   |
Choose Strategy
   |
   +--> CreditCardPayment
   |
   +--> UPIPayment
   |
   +--> PayPalPayment
   |
   v

PaymentService
   |
   v

Execute Strategy
```

---

# Production Example

## Insurance Premium Collection

Bandhan Life Example

Users can pay premium using:

```text id="4v0m2n"
UPI
Credit Card
Debit Card
Net Banking
```

Instead of:

```java id="3k8tq7"
if(paymentType.equals("UPI"))
```

Use:

```java id="7cn7xt"
PaymentStrategy
```

Each payment method becomes a strategy.

---

# Spring Boot Examples

Strategy Pattern is heavily used in Spring.

## Comparator

```java id="5bkppr"
employees.sort(
    Comparator.comparing(
        Employee::getSalary));
```

Change strategy:

```java id="rjzyq4"
employees.sort(
    Comparator.comparing(
        Employee::getName));
```

Comparator is a Strategy.

---

## Authentication Providers

```text id="24d01t"
DaoAuthenticationProvider
LdapAuthenticationProvider
JwtAuthenticationProvider
```

All implement:

```java id="kz8zhr"
AuthenticationProvider
```

Different authentication strategies.

---

# Strategy vs Template Method

| Strategy Pattern             | Template Method         |
| ---------------------------- | ----------------------- |
| Uses Composition             | Uses Inheritance        |
| Runtime Selection            | Compile-Time Structure  |
| Algorithm Changes Completely | Structure Remains Fixed |
| Flexible                     | Less Flexible           |

---

# Advantages

## Open Closed Principle

Add new strategy without changing existing code.

---

## Easy Testing

Each strategy can be tested independently.

---

## Removes if-else Chains

Cleaner code.

---

## Runtime Flexibility

Behavior can change dynamically.

---

## Reusable

Strategies can be reused across applications.

---

# Disadvantages

## More Classes

Each algorithm becomes a separate class.

---

## Client Chooses Strategy

Client must know which strategy to use.

---

## Slightly Complex

Can be overkill for very simple logic.

---

# Interview Questions

## What is Strategy Pattern?

Strategy Pattern encapsulates multiple algorithms into separate classes and allows selecting them dynamically at runtime.

---

## When should you use it?

When there are multiple ways to perform the same task.

Examples:

* Payment Processing
* Sorting
* Discount Calculation
* Tax Calculation
* Authentication
* File Compression

---

## Which SOLID Principle does it support?

* Open Closed Principle (OCP)
* Dependency Inversion Principle (DIP)

---

## Difference Between Strategy and Template Method?

Strategy allows complete algorithm replacement at runtime.

Template Method keeps the algorithm structure fixed and only allows specific steps to vary.

---

# Memory Tricks

## Trick 1

```text id="2ijh92"
ONE TASK
    ↓
MANY WAYS
    ↓
CHOOSE ONE
```

Strategy Pattern.

---

## Trick 2

```text id="l0pwlf"
Google Maps

Destination Same
Route Different
```

Strategy Pattern.

---

## Trick 3

```text id="sy6dk0"
Payment

Credit Card
UPI
PayPal
Google Pay
```

Choose one dynamically.

Strategy Pattern.

---

# Quick Revision

```text id="z0ogza"
Strategy Pattern

Same Task
    ↓
Different Algorithms
    ↓
Choose One At Runtime

Examples:

Payment Methods
Sorting
Discount Calculation
Tax Calculation
Authentication

Google Maps Route Selection

ONE TASK
↓
MANY WAYS
↓
CHOOSE ONE
```

---

# Ultimate Memory Formula

```text id="24y2lx"
Observer Pattern
ONE → MANY

Strategy Pattern
ONE TASK → MANY ALGORITHMS
```

Examples:

Observer:

```text id="9h7jwc"
Policy Approved
      ↓
Email
SMS
Audit
Dashboard
```

Strategy:

```text id="u0mjlwm"
Pay Premium
      ↓
UPI
Card
Net Banking
PayPal
```
