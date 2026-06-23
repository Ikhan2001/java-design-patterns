# Proxy Pattern - Real Production Examples

## Why This Example Is Better Than Image Loading

The classic Image Proxy example is useful for learning, but in real-world enterprise applications, Proxy Pattern is commonly used for:

* Lazy Loading
* Security
* Access Control
* Transaction Management
* Remote Service Communication
* API Gateways

Let's understand Proxy Pattern through practical Spring Boot and Microservice examples.

---

# Virtual Proxy Example - Lazy Loading Large Documents

## Problem

Suppose an Insurance Application stores policy documents in AWS S3.

Each document may be:

```text
50 MB
100 MB
200 MB
```

Loading a document requires:

```text
Database Lookup
        ↓
S3 Download
        ↓
Metadata Processing
        ↓
Document Rendering
```

Time Required:

```text
3 - 5 Seconds
```

---

## Without Proxy

```java
PolicyDocument document =
        new RealPolicyDocument(
                "POL123.pdf"
        );
```

Output:

```text
Loading Document From S3...
Please Wait...
Document Loaded
```

The document is loaded immediately even if the user never opens it.

This wastes:

* Memory
* CPU
* Network Bandwidth

---

## Real Subject

```java
public class RealPolicyDocument
        implements Document {

    private String fileName;

    public RealPolicyDocument(
            String fileName) {

        this.fileName = fileName;

        loadDocument();
    }

    private void loadDocument() {

        System.out.println(
                "Loading Document From S3..."
        );

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }

        System.out.println(
                "Document Loaded"
        );
    }

    @Override
    public void view() {

        System.out.println(
                "Viewing : " + fileName
        );
    }
}
```

---

## Proxy

```java
public class DocumentProxy
        implements Document {

    private String fileName;

    private RealPolicyDocument document;

    public DocumentProxy(
            String fileName) {

        this.fileName = fileName;
    }

    @Override
    public void view() {

        if(document == null) {

            document =
                new RealPolicyDocument(
                        fileName
                );
        }

        document.view();
    }
}
```

---

## Client

```java
public class ProxyExample {

    public static void main(
            String[] args)
            throws Exception {

        Document document =
            new DocumentProxy(
                    "POL123.pdf"
            );

        System.out.println(
                "Application Started"
        );

        Thread.sleep(10000);

        System.out.println(
                "User Opens Document"
        );

        document.view();
    }
}
```

---

## Output

```text
Application Started

(10 seconds pass)

User Opens Document

Loading Document From S3...

Document Loaded

Viewing : POL123.pdf
```

Notice:

```text
Document is loaded only
when user actually opens it.
```

This is called:

```text
Virtual Proxy
```

---

# Spring Boot Real Production Example #1

## Hibernate Lazy Loading

This is one of the most common Proxy Pattern implementations in enterprise applications.

### Entity

```java
@Entity
public class Customer {

    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY
    )
    private List<Policy> policies;
}
```

---

### Fetch Customer

```java
Customer customer =
        customerRepository
                .findById(1L)
                .get();
```

Hibernate loads:

```text
Customer
```

but does NOT load:

```text
Policies
```

---

### Internal Structure

```text
Customer
    ↓
Hibernate Proxy
```

---

Only when:

```java
customer.getPolicies();
```

Hibernate executes:

```sql
select *
from policy
where customer_id = ?
```

and loads policies.

---

### Interview Point

Whenever you see:

```java
FetchType.LAZY
```

Think:

```text
Proxy Pattern
```

---

# Spring Boot Real Production Example #2

## Spring AOP

### Service

```java
@Service
public class PaymentService {

    @Transactional
    public void processPayment() {

        System.out.println(
                "Payment Processing"
        );
    }
}
```

---

### What Spring Actually Creates

```text
PaymentServiceProxy
        ↓
PaymentService
```

---

### Execution Flow

```text
Client
   ↓
Proxy
   ↓
PaymentService
```

---

Proxy performs:

```text
Open Transaction
        ↓
Call Real Method
        ↓
Commit Transaction
```

without modifying the original service.

---

### Common Proxy-Based Annotations

```java
@Transactional

@Cacheable

@Retryable
```

All work using Spring-generated proxies.

---

# Spring Boot Real Production Example #3

## Spring Security

### Example

```java
@PreAuthorize(
    "hasRole('ADMIN')"
)
public void deletePolicy() {

}
```

---

### Internal Flow

```text
Client
      ↓
Security Proxy
      ↓
Policy Service
```

---

Proxy checks:

```text
Authentication

Authorization

Permissions
```

before allowing access.

---

If unauthorized:

```text
Access Denied
```

---

This is called:

```text
Protection Proxy
```

---

# Microservice Example #1

## API Gateway

Architecture:

```text
Frontend
    ↓
API Gateway
    ↓
User Service

Policy Service

Payment Service

Notification Service
```

---

Gateway acts like:

```text
Proxy
```

Responsibilities:

```text
Authentication

Authorization

Rate Limiting

Routing

Logging

Monitoring
```

before forwarding requests.

---

### Real Production Example

```text
Mobile App
      ↓
API Gateway
      ↓
Policy Service

Claim Service

Payment Service
```

---

# Microservice Example #2

## Feign Client

### Interface

```java
@FeignClient(
    name = "policy-service"
)
public interface PolicyClient {

    @GetMapping(
        "/policies/{id}"
    )
    Policy getPolicy(
        @PathVariable Long id
    );
}
```

---

Spring automatically creates:

```text
PolicyClientProxy
```

---

### Execution Flow

```java
policyClient.getPolicy(1L);
```

Proxy performs:

```text
Create HTTP Request

Serialize Request

Call Remote Service

Parse Response

Return Java Object
```

---

This is called:

```text
Remote Proxy
```

---

# Types of Proxy

## Virtual Proxy

Purpose:

```text
Lazy Loading
```

Examples:

```text
Hibernate Lazy Loading

Large Images

PDF Documents

Videos
```

---

## Protection Proxy

Purpose:

```text
Security
```

Examples:

```text
Spring Security

Admin APIs

Role Based Access
```

---

## Remote Proxy

Purpose:

```text
Access Remote Systems
```

Examples:

```text
Feign Client

RMI

gRPC

SOAP
```

---

# Interview Gold Answer

## Where Have You Seen Proxy Pattern In Spring Boot?

```text
1. Hibernate Lazy Loading
   (FetchType.LAZY)

2. Spring AOP
   (@Transactional)

3. Spring Security
   (@PreAuthorize)

4. Feign Clients

5. API Gateway

6. Remote Service Communication

7. Large Document/Image Lazy Loading
```

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

Spring Examples:
Hibernate LAZY
@Transactional
Spring Security
Feign Client
API Gateway

Benefits:
Lazy Loading
Security
Performance
Access Control
```

---

# Memory Trick

```text
Customer
   ↓
Security Guard
   ↓
Bank Locker
```

The Security Guard controls access.

That Security Guard is the Proxy.

---

# Ultimate Formula

```text
Decorator
ADD BEHAVIOR

Facade
SIMPLIFY COMPLEXITY

Proxy
CONTROL ACCESS
```
