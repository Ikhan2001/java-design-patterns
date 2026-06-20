package design.pattern.behavioral.templatepattern.parser;

public abstract class DataParser {

    public final void parseData(){
        readFile();
        parseFile();
        processData();
        saveData();
    }
    public void readFile(){
        System.out.println("Reading from file...");
    }

    public abstract void parseFile();

    public void processData(){
        System.out.println("Processing the data....");
    }

    public void saveData(){
        System.out.println("Saving the data .....");
    }

}
