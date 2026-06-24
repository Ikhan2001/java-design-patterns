package design.pattern.structural.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Department implements OrganizationComponent{
    private String name;
    private List<OrganizationComponent> children = new ArrayList<>();

    public Department(String name){
        this.name = name;
    }

    public void add(OrganizationComponent component){
        children.add(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Department: "+name);
        for(OrganizationComponent child : children){
            child.showDetails();
        }
    }
}
