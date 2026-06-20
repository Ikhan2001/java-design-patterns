package design.pattern.behavioral.commnandpattern;

import design.pattern.behavioral.commnandpattern.command.Command;

public class Toolbar {
    private Command command;

    public Toolbar(Command command) {
        this.command = command;
    }

    public void click() {
        command.execute();
    }
}
