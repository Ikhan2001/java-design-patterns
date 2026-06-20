package design.pattern.behavioral.commnandpattern.command;

import design.pattern.behavioral.commnandpattern.TextEditor;

public class CopyCommand implements Command{

    private TextEditor editor;

    public CopyCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.copy();
    }

    @Override
    public void undo() {
        System.out.println("Undo Copy");
    }
}
