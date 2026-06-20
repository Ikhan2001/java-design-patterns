package design.pattern.behavioral.commnandpattern.command;

import design.pattern.behavioral.commnandpattern.TextEditor;

public class PasteCommand implements Command {
    private TextEditor editor;

    public PasteCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.paste();
    }

    @Override
    public void undo() {
        System.out.println("Undo Paste");
    }
}
