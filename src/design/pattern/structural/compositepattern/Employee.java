package design.pattern.structural.compositepattern;

public class Employee implements OrganizationComponent{

    private String name;

    public Employee(String name){
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Employee: "+name);
    }
}
