package dyn;

public class LogPrintable implements Printable {

    private Printable printable;

    public LogPrintable(Printable printable) {
        this.printable = printable;
    }

    @Override
    public String print() {
        long start = System.currentTimeMillis();
        String ret = printable.print();
        System.out.println(System.currentTimeMillis() - start);
        return ret;
    }
}
