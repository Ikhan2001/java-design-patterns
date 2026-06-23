# Chain of Responsibility Pattern

## Category

Behavioral Design Pattern

---

# Definition

The Chain of Responsibility Pattern allows a request to pass through a chain of handlers until one of them handles it.

Each handler decides:

```text
Can I Handle This?
      |
   Yes → Handle
      |
   No
      ↓
Pass To Next Handler
```

---

# Simple Definition

```text
REQUEST
   ↓
Handler 1
   ↓
Handler 2
   ↓
Handler 3
   ↓
Handled
```

---

# Real-Life Analogy

Think about a Customer Support System.

```text
Customer
    ↓
L1 Support
    ↓
L2 Support
    ↓
Manager
```

If L1 cannot solve the issue:

```text
Pass To L2
```

If L2 cannot solve:

```text
Pass To Manager
```

Eventually someone handles the request.

This is Chain of Responsibility Pattern.

---

# Problem Statement

Suppose a support system handles:

```text
Password Reset
Database Issue
Refund Request
```

Bad Design:

```java
public void processRequest(String issue){

    if(issue.equals("PASSWORD")){
        l1.handle();
    }
    else if(issue.equals("DATABASE")){
        l2.handle();
    }
    else if(issue.equals("REFUND")){
        manager.handle();
    }
}
```

---

# Problems

* Huge if-else chains
* Tight Coupling
* Difficult to extend
* Hard to maintain

---

# Violates Open Closed Principle

Every new support level requires:

```java
else if(...)
```

Existing code must be modified.

---

# Solution

Create a chain of handlers.

```text
L1 Support
      ↓
L2 Support
      ↓
Manager
```

Each handler decides:

```text
Handle
or
Pass Forward
```

---

# UML Diagram

```text
                 +----------------+
                 |    Handler     |
                 +----------------+
                 | handle()       |
                 | nextHandler    |
                 +-------+--------+
                         ^
         ---------------------------------
         |               |               |
         v               v               v

     L1Support      L2Support      ManagerSupport
```

---

# Components

## Handler

Common contract.

```java
handle()
```

---

## Concrete Handlers

Actual processors.

```text
L1Support
L2Support
ManagerSupport
```

---

## Client

Creates requests.

---

## Chain

Links handlers together.

```text
L1 → L2 → Manager
```

---

# Working Example

## Support Request

```java
public class SupportRequest {

    private String issueType;

    public SupportRequest(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueType() {
        return issueType;
    }
}
```

---

## Abstract Handler

```java
public abstract class SupportHandler {

    protected SupportHandler nextHandler;

    public void setNextHandler(
            SupportHandler nextHandler) {

        this.nextHandler = nextHandler;
    }

    public abstract void handle(
            SupportRequest request);
}
```

---

## L1 Support

```java
public class L1Support
        extends SupportHandler {

    @Override
    public void handle(
            SupportRequest request) {

        if("PASSWORD".equals(
                request.getIssueType())) {

            System.out.println(
                    "L1 handled Password Reset"
            );
        }
        else if(nextHandler != null) {

            nextHandler.handle(request);
        }
    }
}
```

---

## L2 Support

```java
public class L2Support
        extends SupportHandler {

    @Override
    public void handle(
            SupportRequest request) {

        if("DATABASE".equals(
                request.getIssueType())) {

            System.out.println(
                    "L2 handled Database Issue"
            );
        }
        else if(nextHandler != null) {

            nextHandler.handle(request);
        }
    }
}
```

---

## Manager Support

```java
public class ManagerSupport
        extends SupportHandler {

    @Override
    public void handle(
            SupportRequest request) {

        System.out.println(
                "Manager handled : "
                + request.getIssueType()
        );
    }
}
```

---

## Client

```java
public class ChainExample {

    public static void main(
            String[] args) {

        SupportHandler l1 =
                new L1Support();

        SupportHandler l2 =
                new L2Support();

        SupportHandler manager =
                new ManagerSupport();

        l1.setNextHandler(l2);
        l2.setNextHandler(manager);

        l1.handle(
                new SupportRequest(
                        "PASSWORD"));

        l1.handle(
                new SupportRequest(
                        "DATABASE"));

        l1.handle(
                new SupportRequest(
                        "REFUND"));
    }
}
```

---

# Output

```text
L1 handled Password Reset

L2 handled Database Issue

Manager handled : REFUND
```

---

# Execution Flow

## Request 1

```text
PASSWORD

    ↓

L1

Can Handle?
    ↓

YES

Handled
```

---

## Request 2

```text
DATABASE

    ↓

L1

Can Handle?
    ↓

NO

Pass To L2

    ↓

YES

Handled
```

---

## Request 3

```text
REFUND

    ↓

L1
 ↓
L2
 ↓
Manager

Handled
```

---

# Production Example

## Customer Support System

```text
Customer Request
      ↓
L1 Support
      ↓
L2 Support
      ↓
Manager
```

Exactly Chain of Responsibility.

---

## Spring Security Filter Chain

Before request reaches controller:

```text
Authentication Filter
        ↓
Authorization Filter
        ↓
JWT Filter
        ↓
Controller
```

Each filter decides:

```text
Continue
or
Reject
```

One of the most common real-world examples.

---

## Logging Framework

```text
INFO
WARN
ERROR
FATAL
```

Logger Chain:

```text
Console Logger
      ↓
File Logger
      ↓
Database Logger
```

Each logger decides whether to process the request.

---

## Insurance Approval Workflow

Bandhan Life Example

```text
Agent
  ↓
Team Lead
  ↓
Manager
  ↓
Regional Head
```

Approval request moves upward until approved.

Perfect Chain of Responsibility use case.

---

# Chain of Responsibility vs Command

| Chain of Responsibility        | Command                     |
| ------------------------------ | --------------------------- |
| Who will handle request?       | What action should execute? |
| Request flows through handlers | Request becomes object      |
| Focus on routing               | Focus on execution          |
| Multiple handlers              | Single command execution    |

---

## Command Example

```text
Copy
Paste
Cut
```

---

## Chain Example

```text
L1
 ↓
L2
 ↓
Manager
```

---

# Chain of Responsibility vs Observer

## Observer

```text
ONE
 ↓
MANY
```

Notify everyone.

---

## Chain

```text
ONE
 ↓
ONE
 ↓
ONE
```

Pass until handled.

---

# Advantages

## Loose Coupling

Sender doesn't know receiver.

---

## Open Closed Principle

Add new handlers without changing existing code.

---

## Flexible Order

Handlers can be rearranged easily.

---

## Cleaner Code

Removes huge if-else chains.

---

## Dynamic Chains

Handlers can be added or removed at runtime.

---

# Disadvantages

## Performance Cost

Request may travel through multiple handlers.

---

## Hard Debugging

Need to identify which handler processed the request.

---

## Unhandled Requests

Possible if nobody handles the request.

---

## Long Chains

Can become difficult to maintain.

---

# Interview Questions

## What is Chain of Responsibility Pattern?

Chain of Responsibility allows a request to pass through multiple handlers until one handler processes it.

---

## When should you use it?

When multiple objects can handle a request and the sender should not know which one will process it.

---

## Which SOLID Principles does it support?

```text
Open Closed Principle

Single Responsibility Principle
```

---

## Real World Examples

```text
Spring Security Filter Chain

Customer Support System

Approval Workflow

Logging Framework

Servlet Filters
```

---

# Memory Tricks

## Trick 1

```text
I Can't Handle
      ↓
Pass It Forward
```

---

## Trick 2

```text
L1
 ↓
L2
 ↓
Manager
```

Escalation Flow.

---

## Trick 3

```text
Request
 ↓
Pass
 ↓
Pass
 ↓
Handle
```

Chain of Responsibility.

---

# Quick Revision

```text
Chain of Responsibility Pattern

Request
   ↓
Handler
   ↓
Handler
   ↓
Handler

Handle
or
Pass Forward

Examples:

Customer Support
Spring Security Filters
Logging Framework
Approval Workflow

REQUEST
↓
PASS UNTIL HANDLED
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

Chain of Responsibility
REQUEST
↓
PASS UNTIL HANDLED
```
