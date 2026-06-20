package design.pattern.behavioral.commnandpattern;

import design.pattern.behavioral.commnandpattern.command.Command;
import design.pattern.behavioral.commnandpattern.command.CopyCommand;
import design.pattern.behavioral.commnandpattern.command.PasteCommand;

public class CommandExample {
    public static void main(String[] args) {

        TextEditor editor = new TextEditor();

        Command copy = new CopyCommand(editor);
        Command paste = new PasteCommand(editor);

        Toolbar toolbar = new Toolbar(copy);
        toolbar.click();
        toolbar = new Toolbar(paste);
        toolbar.click();
    }
}
