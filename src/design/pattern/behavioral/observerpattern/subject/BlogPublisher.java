package design.pattern.behavioral.observerpattern.subject;

public class BlogPublisher extends SubjectImpl {

    @Override
    public void publish(String post){
        System.out.println("New Blog Published : " + post);
        notifyObservers(post);
    }
}