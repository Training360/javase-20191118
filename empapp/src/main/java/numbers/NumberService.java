package numbers;

import java.util.List;

public class NumberService {

    public NumberCounter count(List<Integer> numbers) {
        return numbers
                .stream()
                .parallel()
                .collect(NumberCounter::new, NumberCounter::increment,
                        NumberCounter::add);
    }

    public static void main(String[] args) {
        System.out.println(new NumberService().count(List.of(-1, -1, -1, 0, 0, +1)));
    }
}
