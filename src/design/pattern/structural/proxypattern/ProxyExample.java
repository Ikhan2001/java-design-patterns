package design.pattern.structural.proxypattern;

public class ProxyExample {
    public static void main(String[] args) {
        Image image = new ProxyImage("policy.pdf");
        System.out.println("Object created!!!");
        image.display();
        image.display();
    }
}
