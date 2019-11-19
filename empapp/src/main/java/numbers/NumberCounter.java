package numbers;

public class NumberCounter {

    private int negative;

    private int zero;

    private int positive;

    public void increment(int i) {
        if (i < 0) {
            negative++;
        }
        else if (i > 0) {
            positive ++;
        }
        else {
            zero++;
        }
    }

    public void add(NumberCounter numberCounter) {
        negative += numberCounter.negative;
        zero += numberCounter.zero;
        positive += numberCounter.positive;
        System.out.println("ADDDD");
    }

    public int getNegative() {
        return negative;
    }

    public int getZero() {
        return zero;
    }

    public int getPositive() {
        return positive;
    }

    @Override
    public String toString() {
        return "NumberCounter{" +
                "negative=" + negative +
                ", zero=" + zero +
                ", positive=" + positive +
                '}';
    }
}
