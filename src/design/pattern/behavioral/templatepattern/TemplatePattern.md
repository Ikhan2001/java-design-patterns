# Template Method Pattern

## Category

Behavioral Design Pattern

---

# Definition

The Template Method Pattern defines the skeleton of an algorithm in a parent class while allowing subclasses to redefine specific steps without changing the overall algorithm structure.

In simple words:

```text
SAME PROCESS
      ↓
SOME STEPS CHANGE
      ↓
STRUCTURE REMAINS FIXED
```

---

# Real-Life Analogy

Think of preparing beverages.

Tea:

```text
Boil Water
Add Tea Leaves
Pour Into Cup
Add Lemon
```

Coffee:

```text
Boil Water
Add Coffee Powder
Pour Into Cup
Add Sugar
```

Common Steps:

```text
Boil Water
Pour Into Cup
```

Different Steps:

```text
Tea Leaves
Coffee Powder
```

The overall recipe remains the same.

Only specific steps change.

This is Template Method Pattern.

---

# Why Do We Need It?

## Bad Design

Suppose we support multiple file formats.

```java
CSVParser
XMLParser
JSONParser
```

Each class contains:

```text
Read File
Parse File
Process Data
Save Data
```

The same workflow gets repeated in every class.

---

# Problems

Duplicate logic:

```text
Read File
Process Data
Save Data
```

appears everywhere.

Violates:

```text
DRY Principle
(Don't Repeat Yourself)
```

---

# Solution

Move common workflow into a parent class.

Allow subclasses to customize only the varying step.

```text
               DataParser
                    |
      -----------------------------
      |             |            |
      v             v            v

 CSVParser     XMLParser    JSONParser
```

---

# UML Diagram

```text
                 +------------------+
                 |   DataParser     |
                 +------------------+
                 | parseData()      |
                 | readFile()       |
                 | processData()    |
                 | saveData()       |
                 | parseFile()      |
                 +---------+--------+
                           ^
       ----------------------------------------
       |                   |                  |
       v                   v                  v

+---------------+  +---------------+  +---------------+
| CSVParser     |  | XMLParser     |  | JSONParser    |
+---------------+  +---------------+  +---------------+
| parseFile()   |  | parseFile()   |  | parseFile()   |
+---------------+  +---------------+  +---------------+
```

---

# Components

## Abstract Class

Contains template method.

```java
DataParser
```

---

## Template Method

Defines workflow.

```java
parseData()
```

Workflow:

```text
Read
Parse
Process
Save
```

---

## Concrete Classes

Implement custom behavior.

```java
CSVParser
XMLParser
JSONParser
```

---

# Working Example

## Abstract Template

```java
public abstract class DataParser {

    public final void parseData() {

        readFile();

        parseFile();

        processData();

        saveData();
    }

    private void readFile() {
        System.out.println("Reading File...");
    }

    protected abstract void parseFile();

    private void processData() {
        System.out.println("Processing Data...");
    }

    private void saveData() {
        System.out.println("Saving Data...");
    }
}
```

---

## CSV Parser

```java
public class CSVParser extends DataParser {

    @Override
    protected void parseFile() {

        System.out.println(
                "Parsing CSV File"
        );
    }
}
```

---

## XML Parser

```java
public class XMLParser extends DataParser {

    @Override
    protected void parseFile() {

        System.out.println(
                "Parsing XML File"
        );
    }
}
```

---

## JSON Parser

```java
public class JSONParser extends DataParser {

    @Override
    protected void parseFile() {

        System.out.println(
                "Parsing JSON File"
        );
    }
}
```

---

## Client

```java
public class TemplateMethodExample {

    public static void main(String[] args) {

        DataParser parser =
                new CSVParser();

        parser.parseData();

        System.out.println(
                "---------------"
        );

        parser =
                new XMLParser();

        parser.parseData();

        System.out.println(
                "---------------"
        );

        parser =
                new JSONParser();

        parser.parseData();
    }
}
```

---

# Output

```text
Reading File...
Parsing CSV File
Processing Data...
Saving Data...

---------------

Reading File...
Parsing XML File
Processing Data...
Saving Data...

---------------

Reading File...
Parsing JSON File
Processing Data...
Saving Data...
```

---

# Execution Flow

## CSV

```text
parseData()
      |
      v
Read File
      |
      v
Parse CSV
      |
      v
Process Data
      |
      v
Save Data
```

---

## XML

```text
parseData()
      |
      v
Read File
      |
      v
Parse XML
      |
      v
Process Data
      |
      v
Save Data
```

Notice:

```text
Read
Process
Save
```

remain same.

Only:

```text
Parse
```

changes.

---

# Why Is Template Method Final?

```java
public final void parseData()
```

Reason:

```text
Prevent subclasses
from changing workflow.
```

Subclasses should customize steps.

They should not change:

```text
Read
Parse
Process
Save
```

sequence.

---

# Production Example

## Insurance Report Generation

Bandhan Life Example

Every report follows:

```text
Fetch Data
Validate Data
Generate Report
Export Report
```

Reports:

```text
Policy Report
Claim Report
Customer Report
Medical Report
```

Workflow remains same.

Only data retrieval and processing differ.

Perfect Template Method use case.

---

# Spring Boot Example

Spring uses Template Method heavily.

## JdbcTemplate

Spring handles:

```text
Open Connection
Execute Query
Handle Exceptions
Close Connection
```

Developer customizes:

```java
RowMapper
```

Workflow remains fixed.

Customization is allowed.

---

# Template Method vs Strategy

| Template Method      | Strategy                 |
| -------------------- | ------------------------ |
| Uses Inheritance     | Uses Composition         |
| Workflow Fixed       | Algorithm Changes        |
| Parent Controls Flow | Client Chooses Algorithm |
| Some Steps Change    | Entire Algorithm Changes |

---

## Strategy Example

```text
Payment

UPI
Credit Card
PayPal
```

Entire algorithm changes.

---

## Template Method Example

```text
Generate Report

Fetch Data
Process Data
Export Report
```

Workflow fixed.

Only some steps change.

---

# Advantages

## Code Reuse

Common logic written once.

---

## Consistent Workflow

Parent controls process.

---

## Reduces Duplication

No repeated code.

---

## Easy Maintenance

Changes in one place.

---

## Enforces Process

Subclasses cannot break flow.

---

# Disadvantages

## Inheritance Based

Can create deep hierarchies.

---

## Less Flexible

Workflow cannot change dynamically.

---

## More Classes

Every variation becomes a subclass.

---

# Interview Questions

## What is Template Method Pattern?

Template Method Pattern defines the skeleton of an algorithm and allows subclasses to customize specific steps while keeping the overall workflow unchanged.

---

## When should you use it?

When:

```text
Workflow remains same
Specific steps vary
```

Examples:

* File Parsing
* Report Generation
* Data Processing
* Spring JdbcTemplate
* Document Export

---

## Which Principle Does It Support?

```text
DRY Principle
Hollywood Principle
```

### Hollywood Principle

```text
Don't call us,
we'll call you.
```

Parent controls flow and invokes child methods.

---

## Difference Between Strategy and Template Method?

Strategy:

```text
Entire Algorithm Changes
```

Template Method:

```text
Algorithm Fixed
Some Steps Change
```

---

# Memory Tricks

## Trick 1

```text
SAME PROCESS
      ↓
STEP CHANGES
```

Template Method.

---

## Trick 2

```text
Tea Recipe

Boil
Add Ingredient
Pour
```

Only ingredient changes.

Template Method.

---

## Trick 3

```text
CSV
XML
JSON

Read
Parse
Process
Save
```

Only Parse changes.

Template Method.

---

# Quick Revision

```text
Template Method Pattern

Algorithm Fixed
      ↓
Specific Steps Change

Examples:

Report Generation
File Parsing
Data Processing
Spring JdbcTemplate

Uses Inheritance

Parent Controls Workflow

Child Customizes Steps

SAME PROCESS
      ↓
SOME STEPS CHANGE
```

---

# Ultimate Memory Formula

```text
Observer
ONE → MANY

Strategy
ONE TASK → MANY ALGORITHMS

Template Method
SAME ALGORITHM
↓
DIFFERENT STEPS
```
