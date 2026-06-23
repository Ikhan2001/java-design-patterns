package design.pattern.structural.proxypattern;

public class RealImage implements Image{

    private String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk(){
        System.out.println("Loading Image: "+fileName);
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Image Loaded successfully!!!");
    }

    @Override
    public void display() {
        System.out.println("Displaying Image: "+fileName);
    }
}
