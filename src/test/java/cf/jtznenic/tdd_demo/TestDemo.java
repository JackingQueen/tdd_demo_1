package cf.jtznenic.tdd_demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestDemo {

//    @Test
    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "2, 1", "3, 2"})
    public void testFibonacci(int index, int expect) {
        assertThat(fib(index), equalTo(expect));
    }

    private int fib(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 2) {
            return 1;
        }
        return fib(i - 1) + fib(i - 2);
    }
}
