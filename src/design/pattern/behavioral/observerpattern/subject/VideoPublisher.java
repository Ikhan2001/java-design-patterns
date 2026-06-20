package design.pattern.behavioral.observerpattern.subject;

public class VideoPublisher extends SubjectImpl{

    @Override
    public void publish(String video){
        System.out.println("New video is  Published : " + video);
        notifyObservers(video);
    }
}
