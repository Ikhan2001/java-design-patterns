# Spring Boot & Microservices Use Cases

Decorator Pattern is heavily used in enterprise applications, Spring Framework, and microservices to add behavior dynamically without modifying existing code.

---

## 1. Spring Security Filters (Most Important)

Before a request reaches the controller, it passes through multiple layers.

```text
HTTP Request
      ↓
Authentication Filter
      ↓
Authorization Filter
      ↓
JWT Validation Filter
      ↓
Controller
```

Each filter adds additional behavior before forwarding the request.

Examples:

```java
OncePerRequestFilter
GenericFilterBean
```

Custom JWT Filter:

```java
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {
}
```

Behavior Added:

```text
Request
    ↓
Authentication
    ↓
Authorization
    ↓
JWT Validation
```

This is one of the most common Decorator-like implementations in Spring Boot.

---

## 2. RestTemplate / WebClient Interceptors

Base API Call:

```java
restTemplate.getForObject(...)
```

Additional Features:

```text
Logging
Authentication Header
Tracing
Monitoring
Correlation Id
```

Example:

```java
ClientHttpRequestInterceptor
```

```java
public class LoggingInterceptor
        implements ClientHttpRequestInterceptor {
}
```

Every interceptor adds extra behavior around the original request.

---

## 3. Notification Service (Microservices)

Policy Approved Event:

```text
Policy Approved
      ↓
Email
```

Premium Customer:

```text
Email
 +
SMS
```

VIP Customer:

```text
Email
 +
SMS
 +
WhatsApp
```

Mobile User:

```text
Email
 +
SMS
 +
WhatsApp
 +
Push Notification
```

Implementation:

```java
Notification notification =
        new EmailNotification();

notification =
        new SmsDecorator(notification);

notification =
        new WhatsAppDecorator(notification);

notification =
        new PushNotificationDecorator(notification);

notification.send();
```

Benefits:

```text
New Notification Channel
         ↓
Create New Decorator

No Existing Code Changes
```

---

## 4. Logging & Auditing Wrappers

Base Service:

```java
PolicyService
```

Add:

```text
Logging
Auditing
Performance Monitoring
```

Visualization:

```text
PolicyService
      ↓
LoggingDecorator
      ↓
AuditDecorator
      ↓
MetricsDecorator
```

Each layer adds additional functionality.

---

## 5. Compression & Encryption Services

Before storing policy documents:

```text
Policy Document
      ↓
Compression
      ↓
Encryption
      ↓
Upload To S3
```

Decorator Structure:

```text
DocumentService
      ↓
CompressionDecorator
      ↓
EncryptionDecorator
```

Common in:

```text
Insurance
Banking
Healthcare
Financial Applications
```

---

## 6. Java IO Streams (Classic JDK Example)

The most famous Decorator Pattern example.

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

Each wrapper adds new behavior:

```text
Buffering
Data Reading
Additional Utility Methods
```

without modifying the original stream.

---

## 7. Feign Client Enhancements

Base Feign Client:

```java
@FeignClient
```

Additional Features:

```text
Retry
Logging
Metrics
Distributed Tracing
```

Decorator-like behavior around outbound service calls.

---

# Real Production Example (Bandhan Life)

Policy Approved:

```text
Policy Approved Event
       ↓
Email Notification
       ↓
SMS Notification
       ↓
WhatsApp Notification
       ↓
Push Notification
```

Implementation:

```java
Notification notification =
        new EmailNotification();

notification =
        new SmsDecorator(notification);

notification =
        new WhatsAppDecorator(notification);

notification =
        new PushNotificationDecorator(notification);

notification.send();
```

Advantages:

```text
New Notification Type?
        ↓
Create New Decorator

No Existing Code Changes
```

Supports:

```text
Open Closed Principle

Composition Over Inheritance
```

---

# Interview Gold Answer

### Where have you seen Decorator Pattern in Spring Boot?

Answer:

```text
1. Java IO Streams
   FileInputStream
       ↓
   BufferedInputStream
       ↓
   DataInputStream

2. Spring Security Filter Chain

3. RestTemplate/WebClient Interceptors

4. Notification Systems
   Email → SMS → WhatsApp

5. Logging & Auditing Wrappers

6. Compression & Encryption Services

7. Feign Client Enhancements
```

This answer usually impresses interviewers because it connects the pattern to real production systems instead of only academic examples.
