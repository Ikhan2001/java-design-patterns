# Composite Pattern

## Category

Structural Design Pattern

---

# Definition

The Composite Pattern allows you to compose objects into tree-like structures and treat individual objects and groups of objects uniformly.

It enables clients to work with both single objects and collections of objects using the same interface.

---

# One-Line Definition

```text
PART
AND
WHOLE
ARE TREATED THE SAME
```

or

```text
LEAF
  +
COMPOSITE
  ↓
COMMON INTERFACE
```

---

# Purpose

The Composite Pattern allows clients to treat:

```text
Single Object
```

and

```text
Group Of Objects
```

in the same way.

It is ideal for representing hierarchical structures.

---

# Common Use Cases

* File Systems
* Organization Hierarchies
* Menu Structures
* UI Components
* Category Trees
* Permission Hierarchies
* Comment Threads

---

# Real-Life Analogy

Think about a Company Structure.

```text
Company

├── Engineering
│   ├── John
│   ├── David
│
├── HR
│   ├── Alice
│   ├── Bob
```

Here:

```text
Employee = Leaf

Department = Composite
```

Both are treated as:

```text
OrganizationComponent
```

---

# Problem Statement

Suppose we are building an Organization Management System.

We have:

```text
Employees
```

and

```text
Departments
```

Departments can contain:

```text
Employees

Sub Departments
```

---

Example:

```text
Company

├── Engineering
│   ├── John
│   ├── David
│
├── HR
│   ├── Alice
│   ├── Bob
```

---

Without Composite:

```java
if(object instanceof Employee){
    ...
}

if(object instanceof Department){
    ...
}
```

Client must handle each type differently.

---

# Solution

Create a common abstraction:

```java
OrganizationComponent
```

Both:

```text
Employee

Department
```

implement it.

Now:

```java
component.showDetails();
```

works for both.

---

# UML Diagram

```text
               OrganizationComponent
                       |
              -----------------
              |               |
              v               v

          Employee      Department
                              |
                              |
                       List<Component>
```

---

# Components

## Component

Common interface.

```java
OrganizationComponent
```

---

## Leaf

Individual object.

```java
Employee
```

---

## Composite

Contains child components.

```java
Department
```

---

## Client

Uses Component interface.

```java
OrganizationComponent component
```

---

# Working Example

## Component

```java
public interface OrganizationComponent {

    void showDetails();
}
```

---

## Leaf

```java
public class Employee
        implements OrganizationComponent {

    private String name;

    public Employee(
            String name) {

        this.name = name;
    }

    @Override
    public void showDetails() {

        System.out.println(
                "Employee : "
                + name
        );
    }
}
```

---

## Composite

```java
import java.util.ArrayList;
import java.util.List;

public class Department
        implements OrganizationComponent {

    private String name;

    private List<OrganizationComponent>
            children =
            new ArrayList<>();

    public Department(
            String name) {

        this.name = name;
    }

    public void add(
            OrganizationComponent component) {

        children.add(component);
    }

    @Override
    public void showDetails() {

        System.out.println(
                "Department : "
                + name
        );

        for(
                OrganizationComponent child
                : children
        ) {
            child.showDetails();
        }
    }
}
```

---

## Client

```java
public class CompositeExample {

    public static void main(
            String[] args) {

        Employee john =
                new Employee("John");

        Employee david =
                new Employee("David");

        Employee alice =
                new Employee("Alice");

        Department engineering =
                new Department(
                        "Engineering"
                );

        engineering.add(john);
        engineering.add(david);

        Department hr =
                new Department("HR");

        hr.add(alice);

        Department company =
                new Department("Company");

        company.add(engineering);
        company.add(hr);

        company.showDetails();
    }
}
```

---

# Output

```text
Department : Company

Department : Engineering

Employee : John

Employee : David

Department : HR

Employee : Alice
```

---

# Execution Flow

Memory Structure:

```text
Company
   |
   +---- Engineering
   |          |
   |          +---- John
   |          |
   |          +---- David
   |
   +---- HR
              |
              +---- Alice
```

Client executes:

```java
company.showDetails();
```

Flow:

```text
Company
   ↓

Engineering
   ↓

John

David

HR
   ↓

Alice
```

Recursive traversal happens automatically.

---

# Why Composite?

Without Composite:

```java
if(employee)
{
}

if(department)
{
}
```

Client must know every type.

---

With Composite:

```java
component.showDetails();
```

Works for:

```text
Employee

Department

Sub Department
```

Uniform behavior.

---

# Production Example #1

## Organization Hierarchy

```text
Company

Department

Sub Department

Employees
```

Very common enterprise structure.

---

# Production Example #2

## Menu Structure

```text
Dashboard

Users
    ├── Create User
    ├── Update User

Policies
    ├── Add Policy
    ├── Delete Policy
```

Menu Item:

```text
Leaf
```

Menu Group:

```text
Composite
```

---

# Production Example #3

## File System

```text
Folder

SubFolder

Files
```

Classic Composite example.

---

# Production Example #4

## UI Components

```text
Form

Panel

Button

Textbox

Dropdown
```

Panels contain multiple components.

---

# Spring Boot Example #1

## Role Hierarchy

```text
ADMIN

├── User Management

├── Policy Management

└── Claims Management
```

Roles and permissions form a tree structure.

Composite fits naturally.

---

# Spring Boot Example #2

## Category Tree

E-Commerce Categories:

```text
Electronics

├── Mobiles

├── Laptops

└── Accessories
```

Every category can contain subcategories.

---

# Microservice Example

## Dynamic Navigation Menu

```text
Dashboard

Customer Service

Policy Service

Payment Service
```

Menu Groups and Menu Items are treated uniformly.

---

# Composite vs Decorator

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

## Composite

Purpose:

```text
BUILD TREE STRUCTURE
```

Example:

```text
Company
 ↓
Department
 ↓
Employee
```

---

# Composite vs Bridge

## Bridge

Purpose:

```text
SEPARATE TWO DIMENSIONS
```

Example:

```text
Notification
 ↓
Sender
```

---

## Composite

Purpose:

```text
PARENT CHILD HIERARCHY
```

Example:

```text
Folder
 ↓
File
```

---

# Advantages

## Uniform Treatment

Leaf and Composite handled the same way.

---

## Simplifies Hierarchical Structures

Perfect for tree-based systems.

---

## Open Closed Principle

Add new leaf or composite types easily.

---

## Recursive Operations

Elegant traversal and processing.

---

# Disadvantages

## Harder To Restrict Components

Leaf and Composite share the same interface.

---

## Deep Trees Can Be Complex

Large hierarchies become harder to debug.

---

## More Recursive Calls

Can impact performance in very deep structures.

---

# Most Important Spring Boot Use Cases

```text
Menu Structures

Role Hierarchies

Organization Structures

Category Trees

Permission Trees

UI Components
```

---

# Interview Questions

## What is Composite Pattern?

Composite Pattern allows individual objects and groups of objects to be treated uniformly using a common interface.

---

## When should you use it?

When:

```text
Tree Structure

Hierarchy

Parent Child Relationship

Nested Objects
```

exist in the system.

---

## Which SOLID Principle Does It Support?

```text
Open Closed Principle
```

New components can be added without modifying existing code.

---

# Interview Gold Answer

## Where Have You Used Composite Pattern?

```text
1. Organization Hierarchy

2. File System

3. Menu Structures

4. Role Hierarchies

5. Category Trees

6. UI Components
```

---

# Memory Tricks

## Trick 1

```text
TREE
 ↓
COMPOSITE
```

---

## Trick 2

```text
Folder
 ↓
File
```

Classic example.

---

## Trick 3

```text
Company
 ↓
Department
 ↓
Employee
```

Parent-child hierarchy.

---

# Quick Revision

```text
Composite Pattern

Purpose:
Treat Individual And Group Objects Uniformly

Components:
Component
Leaf
Composite
Client

Examples:
Organization Hierarchy
File System
Menu Structure
Role Hierarchy

Benefits:
Uniform Handling
Tree Structures
Recursive Operations
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

Composite
TREE STRUCTURE
```

## Visual Memory

```text
Company
 ↓
Department
 ↓
Employee
```

Whenever you hear:

```text
Hierarchy

Tree Structure

Parent Child

Folder File

Organization Structure
```

Think **Composite Pattern**.
