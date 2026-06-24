package design.pattern.structural.compositepattern;

public class CompositeExample {
    public static void main(String[] args) {

        Employee intkhab = new Employee("Intkhab");
        Employee saif = new Employee("Saif");
        Employee altaf = new Employee("Altaf");

        Department engineering = new Department("Engineering");
        engineering.add(intkhab);
        engineering.add(saif);

        Department hr = new Department("HR");
        hr.add(altaf);

        Department company = new Department("The Judge Group");
        company.add(engineering);
        company.add(hr);

        company.showDetails();
    }
}
