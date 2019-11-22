package dyn;

public class Document implements Printable {

    @Override
    public String print() {
        System.out.println("printing document");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "return from document";
    }
}
