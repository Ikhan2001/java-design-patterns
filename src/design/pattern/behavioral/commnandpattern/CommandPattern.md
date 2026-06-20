# Command Pattern

## Category

Behavioral Design Pattern

---

# Definition

The Command Pattern encapsulates a request as an object.

Instead of executing an action directly:

```java
editor.copy();
```

We create a command object:

```java
new CopyCommand(editor);
```

This allows the action to be:

* Stored
* Logged
* Queued
* Executed Later
* Undone
* Redone

---

# Simple Definition

```text
ACTION
   ↓
OBJECT
```

or

```text
REQUEST
   ↓
COMMAND OBJECT
```

---

# Real-Life Analogy

Think of a Restaurant.

```text
Customer
    ↓
Order Slip
    ↓
Waiter
    ↓
Chef
```

The Order Slip is the Command.

It contains:

```text
What to do
Who should do it
```

Similarly:

```text
CopyCommand
PasteCommand
CutCommand
```

contain:

```text
Action
Receiver
```

---

# Problem Statement

Suppose we are building a Text Editor.

Supported actions:

```text
Copy
Paste
Cut
Undo
Redo
```

Without Command Pattern:

```java
if(action.equals("COPY")){
    editor.copy();
}

if(action.equals("PASTE")){
    editor.paste();
}

if(action.equals("CUT")){
    editor.cut();
}
```

Adding:

```text
Undo
Redo
History
Macro Recording
```

becomes difficult.

---

# Problems

* Tight Coupling
* Hard to implement Undo/Redo
* Difficult to log actions
* Difficult to queue actions
* Difficult to replay actions

---

# Solution

Convert every action into a Command object.

```text
Copy  → CopyCommand
Paste → PasteCommand
Cut   → CutCommand
```

Now actions become objects.

Objects can be:

```text
Stored
Logged
Queued
Undone
Redone
```

---

# UML Diagram

```text
              +---------------+
              |   Command     |
              +---------------+
              | execute()     |
              | undo()        |
              +-------+-------+
                      ^
      --------------------------------
      |              |              |
      v              v              v

 CopyCommand   PasteCommand   CutCommand

             |
             v

        TextEditor

             ^
             |
          Toolbar
```

---

# Components

## Command

Common contract.

```java
public interface Command {

    void execute();

    void undo();
}
```

---

## Concrete Commands

Actual actions.

```text
CopyCommand
PasteCommand
CutCommand
```

---

## Receiver

Actual object performing work.

```java
TextEditor
```

---

## Invoker

Triggers command execution.

```java
Toolbar
```

---

# Working Example

## Receiver

```java
public class TextEditor {

    public void copy() {
        System.out.println("Copy Text");
    }

    public void paste() {
        System.out.println("Paste Text");
    }

    public void cut() {
        System.out.println("Cut Text");
    }
}
```

---

## Command Interface

```java
public interface Command {

    void execute();

    void undo();
}
```

---

## Copy Command

```java
public class CopyCommand
        implements Command {

    private TextEditor editor;

    public CopyCommand(
            TextEditor editor) {

        this.editor = editor;
    }

    @Override
    public void execute() {

        editor.copy();
    }

    @Override
    public void undo() {

        System.out.println(
                "Undo Copy"
        );
    }
}
```

---

## Paste Command

```java
public class PasteCommand
        implements Command {

    private TextEditor editor;

    public PasteCommand(
            TextEditor editor) {

        this.editor = editor;
    }

    @Override
    public void execute() {

        editor.paste();
    }

    @Override
    public void undo() {

        System.out.println(
                "Undo Paste"
        );
    }
}
```

---

## Cut Command

```java
public class CutCommand
        implements Command {

    private TextEditor editor;

    public CutCommand(
            TextEditor editor) {

        this.editor = editor;
    }

    @Override
    public void execute() {

        editor.cut();
    }

    @Override
    public void undo() {

        System.out.println(
                "Undo Cut"
        );
    }
}
```

---

## Invoker

```java
public class Toolbar {

    private Command command;

    public Toolbar(
            Command command) {

        this.command = command;
    }

    public void click() {

        command.execute();
    }
}
```

---

## Client

```java
public class CommandExample {

    public static void main(String[] args) {

        TextEditor editor =
                new TextEditor();

        Command copy =
                new CopyCommand(editor);

        Command paste =
                new PasteCommand(editor);

        Toolbar toolbar =
                new Toolbar(copy);

        toolbar.click();

        toolbar =
                new Toolbar(paste);

        toolbar.click();

        paste.undo();
    }
}
```

---

# Output

```text
Copy Text

Paste Text

Undo Paste
```

---

# Execution Flow

```text
User Clicks Copy
        |
        v

CopyCommand
        |
        v

Toolbar
        |
        v

execute()
        |
        v

TextEditor.copy()
```

---

# Undo Concept

Every executed command knows:

```text
How to Execute
How to Undo
```

Example:

```java
paste.execute();
```

Later:

```java
paste.undo();
```

Output:

```text
Undo Paste
```

This is the foundation of:

```text
MS Word
VS Code
IntelliJ IDEA
Photoshop
Notepad++
```

Undo/Redo systems.

---

# Production Example

## Text Editors

```text
Copy
Paste
Cut
Undo
Redo
```

Every action becomes a Command.

---

## Insurance System

Bandhan Life Example

Actions:

```text
Approve Policy
Reject Policy
Generate Policy
Send Notification
```

Commands:

```java
ApprovePolicyCommand
RejectPolicyCommand
GeneratePolicyCommand
SendNotificationCommand
```

Commands can be:

```text
Logged
Queued
Scheduled
Audited
```

---

## Job Scheduling

```text
Night Batch Job
      ↓
Run Command
```

Execute later.

---

# Command vs Strategy

| Command              | Strategy                             |
| -------------------- | ------------------------------------ |
| Encapsulates Request | Encapsulates Algorithm               |
| Focuses on Action    | Focuses on Behavior                  |
| Supports Undo/Redo   | Supports Runtime Algorithm Selection |
| execute()            | perform() / pay() / sort()           |

---

## Strategy Example

```text
Payment

UPI
Card
PayPal
```

Different algorithms.

---

## Command Example

```text
Copy
Paste
Cut
```

Different actions.

---

# Advantages

## Loose Coupling

Invoker doesn't know receiver.

---

## Supports Undo/Redo

Most popular use case.

---

## Supports Logging

Store action history.

---

## Supports Scheduling

Execute later.

---

## Supports Queueing

Execute asynchronously.

---

## Open Closed Principle

Add new commands without modifying existing code.

---

# Disadvantages

## Many Classes

Each action becomes a class.

---

## More Complexity

Can be overkill for simple systems.

---

## Additional Objects

Creates command objects for every action.

---

# Interview Questions

## What is Command Pattern?

Command Pattern encapsulates a request as an object, allowing requests to be stored, queued, logged, executed later, and undone.

---

## When should you use it?

When you need:

```text
Undo/Redo
Logging
Scheduling
Queueing
Task Execution
```

---

## Which SOLID Principles does it support?

```text
Open Closed Principle
Dependency Inversion Principle
```

---

## Real World Examples

```text
Text Editors
Remote Controls
Workflow Engines
Job Scheduling
Task Queues
Batch Processing
```

---

# Memory Tricks

## Trick 1

```text
ACTION
   ↓
OBJECT
```

Command Pattern.

---

## Trick 2

```text
Copy
Paste
Cut
```

Become:

```text
CopyCommand
PasteCommand
CutCommand
```

---

## Trick 3

```text
Need Undo?
Need Redo?
Need History?
```

Think:

```text
Command Pattern
```

---

# Quick Revision

```text
Command Pattern

Request
   ↓
Object

Examples:

Copy
Paste
Cut
Undo
Redo

Components:

Command
Concrete Command
Receiver
Invoker

Supports:

Undo
Redo
Logging
Queueing
Scheduling

ACTION
   ↓
OBJECT
```
