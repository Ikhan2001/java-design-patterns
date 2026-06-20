package design.pattern.behavioral.templatepattern.parser;

public class TemplateMethodExample {

    public static void main(String[] args) {
        DataParser parser = new CSVParser();
        parser.parseData();
        System.out.println("---------------");

        parser = new XMLParser();
        parser.parseData();
        System.out.println("---------------");

        parser = new JSONParser();
        parser.parseData();
    }
}
