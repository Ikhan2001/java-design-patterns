# Proxy Pattern

## Category

Structural Design Pattern

---

# Definition

The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.

Instead of accessing the real object directly:

```text
Client
   ↓
Real Object
```

We place a Proxy in between:

```text
Client
   ↓
Proxy
   ↓
Real Object
```

The Proxy controls access before delegating requests to the real object.

---

# One-Line Definition

```text
CONTROL ACCESS
      ↓
TO REAL OBJECT
```

or

```text
Client
   ↓
Proxy
   ↓
Real Object
```

---

# Purpose

The Proxy Pattern provides a placeholder or surrogate for another object to control access to it.

Common reasons include:

* Lazy Loading
* Access Control
* Security
* Logging
* Remote Access
* Performance Optimization

---

# Common Use Cases

* Implementing lazy loading
* Access control and authorization
* Security checks
* Remote service communication
* Logging requests
* Caching expensive objects

---

# Real-Life Analogy

Think of a Bank Locker.

You don't directly access the locker.

Instead:

```text
Customer
    ↓
Security Guard
    ↓
Locker
```

The Security Guard verifies:

```text
Identity

Authorization

Permissions
```

before granting access.

The Security Guard acts as a Proxy.

---

# Problem Statement

Suppose your application displays large images.

Each image may be:

```text
10 MB

20 MB

50 MB
```

Without Proxy:

```java
RealImage image =
        new RealImage("policy.pdf");
```

The image loads immediately.

Problems:

```text
Slow Startup

High Memory Usage

Poor Performance
```

---

# Solution

Create a Proxy.

Instead of:

```java
RealImage image =
        new RealImage("policy.pdf");
```

Use:

```java
Image image =
        new ProxyImage("policy.pdf");
```

The real image loads only when required.

---

# UML Diagram

```text
                 +-------------+
                 |    Image    |
                 +-------------+
                 | display()   |
                 +------+------+
                        ^
            -----------------------
            |                     |
            v                     v

      ProxyImage          RealImage
            |
            |
            +-----------> RealImage
```

---

# Components

## Subject

Common interface.

```java
Image
```

---

## Real Subject

Actual object.

```java
RealImage
```

---

## Proxy

Controls access.

```java
ProxyImage
```

---

## Client

Uses the interface.

```java
Image image;
```

---

# Working Example

## Subject

```java
public interface Image {

    void display();
}
```

---

## Real Object

```java
public class RealImage
        implements Image {

    private String fileName;

    public RealImage(
            String fileName) {

        this.fileName = fileName;

        loadFromDisk();
    }

    private void loadFromDisk() {

        System.out.println(
                "Loading Image : "
                + fileName
        );
    }

    @Override
    public void display() {

        System.out.println(
                "Displaying Image : "
                + fileName
        );
    }
}
```

---

## Proxy

```java
public class ProxyImage
        implements Image {

    private String fileName;

    private RealImage realImage;

    public ProxyImage(
            String fileName) {

        this.fileName = fileName;
    }

    @Override
    public void display() {

        if(realImage == null) {

            realImage =
                    new RealImage(
                            fileName
                    );
        }

        realImage.display();
    }
}
```

---

## Client

```java
public class ProxyExample {

    public static void main(
            String[] args) {

        Image image =
                new ProxyImage(
                        "policy.pdf"
                );

        System.out.println(
                "Object Created"
        );

        image.display();

        image.display();
    }
}
```

---

# Output

```text
Object Created

Loading Image : policy.pdf

Displaying Image : policy.pdf

Displaying Image : policy.pdf
```

Notice:

```text
Loading Image
```

appears only once.

---

# Execution Flow

## Step 1

```java
Image image =
        new ProxyImage(
                "policy.pdf"
        );
```

Memory:

```text
ProxyImage
```

Only proxy object is created.

---

## Step 2

```java
image.display();
```

Proxy checks:

```java
if(realImage == null)
```

True.

Creates:

```java
new RealImage(...)
```

Loads file.

Displays file.

---

## Step 3

```java
image.display();
```

Now:

```java
realImage != null
```

Image already exists.

No reload occurs.

Only display operation executes.

---

# Types Of Proxy

## Virtual Proxy

Provides lazy loading.

Examples:

```text
Large Images

Videos

PDF Files

Reports
```

Loads object only when needed.

---

## Protection Proxy

Provides access control.

Examples:

```text
Admin APIs

Bank Accounts

Policy Management
```

Checks permissions before access.

---

## Remote Proxy

Represents remote objects.

Examples:

```text
RMI

gRPC

SOAP

Remote Services
```

Manages communication with remote systems.

---

# Production Example

## Insurance System

Policy Document:

```text
50 MB PDF
```

Without Proxy:

```text
Load All Documents
```

Huge memory consumption.

---

With Proxy:

```text
Create Proxy

Load Document
ONLY
When User Opens It
```

Improves performance significantly.

---

# Spring Boot Example #1

## Security Protection Proxy

Suppose:

```java
PolicyService
```

should only be accessed by:

```text
ADMIN
```

Proxy:

```java
PolicyProxyService
```

Checks:

```text
User Role

Permissions
```

before forwarding requests.

Visualization:

```text
Client
   ↓
PolicyProxy
   ↓
PolicyService
```

---

# Spring Boot Example #2

## API Gateway

Microservices:

```text
User Service

Policy Service

Payment Service
```

Client:

```text
Frontend
```

Instead of direct access:

```text
Frontend
      ↓
API Gateway
      ↓
Microservices
```

Gateway acts as a Proxy.

Handles:

```text
Authentication

Authorization

Rate Limiting

Logging
```

before forwarding requests.

---

# Spring Boot Example #3

## Hibernate Lazy Loading

Most asked interview example.

Entity:

```java
@OneToMany(fetch = FetchType.LAZY)
private List<Policy> policies;
```

Hibernate creates a Proxy.

Data loads only when accessed:

```java
customer.getPolicies();
```

Perfect Virtual Proxy example.

---

# Spring Boot Example #4

## Feign Client Proxy

```java
@FeignClient
```

Spring generates a proxy object.

Visualization:

```text
Client
   ↓
Feign Proxy
   ↓
Remote Service
```

Proxy handles:

```text
Serialization

HTTP Calls

Response Parsing
```

---

# Proxy vs Decorator

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

Enhances functionality.

---

## Proxy

Purpose:

```text
CONTROL ACCESS
```

Example:

```text
Client
 ↓
Proxy
 ↓
Real Object
```

Controls access.

---

# Proxy vs Facade

## Facade

```text
Many Services
      ↓
One Simple Interface
```

Simplifies complexity.

---

## Proxy

```text
One Object
      ↓
Controlled Access
```

Controls access.

---

# Advantages

## Lazy Loading

Load expensive objects only when needed.

---

## Security

Permission-based access.

---

## Better Performance

Avoid unnecessary object creation.

---

## Logging

Track requests.

---

## Access Control

Protect sensitive operations.

---

# Disadvantages

## Extra Layer

Adds another abstraction.

---

## More Complexity

Additional classes.

---

## Performance Overhead

Every request passes through Proxy.

---

# Interview Questions

## What is Proxy Pattern?

Proxy Pattern provides a surrogate object that controls access to another object.

---

## When should you use it?

When you need:

```text
Lazy Loading

Access Control

Security

Logging

Remote Access
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
Spring Security

API Gateway

Hibernate Lazy Loading

Feign Clients

Virtual Image Loading

Remote Services
```

---

# Memory Tricks

## Trick 1

```text
Client
   ↓
Gatekeeper
   ↓
Real Object
```

Proxy = Gatekeeper.

---

## Trick 2

```text
Need Security?
Need Lazy Loading?
Need Access Control?
```

Think:

```text
Proxy Pattern
```

---

## Trick 3

```text
Load Later
Access Later
Control Access
```

Proxy Pattern.

---

# Quick Revision

```text
Proxy Pattern

Client
   ↓
Proxy
   ↓
Real Object

Purpose:

Control Access

Types:

Virtual Proxy
Protection Proxy
Remote Proxy

Examples:

Hibernate Lazy Loading
API Gateway
Feign Client
Security Layer

Benefits:

Lazy Loading
Security
Performance
Access Control
```

---

# Ultimate Memory Formula

```text
Decorator
ADD BEHAVIOR

Facade
SIMPLIFY COMPLEXITY

Proxy
CONTROL ACCESS
```

## Visual Memory

```text
Customer
   ↓
Security Guard
   ↓
Bank Locker
```

The Security Guard is the Proxy.
