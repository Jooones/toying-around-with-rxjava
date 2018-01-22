package excercise3_2;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;


import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @Before
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void fizz() {
        Observable<String> fizz = fizzBuzz.fizz(Observable.range(10, 25));
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        fizz.subscribe();
        subscriber.awaitTerminalEvent();
        List<String> onNextEvents = subscriber.getOnNextEvents();

        List<String> expected = Lists.newArrayList("10", "11", "Fizz", "13", "14", "Fizz", "16", "17", "Fizz", "19", "20");
        assertThat(onNextEvents).isEqualTo(expected);
    }

    @Test
    public void buzz() {
        Observable<String> fizz = fizzBuzz.buzz(Observable.range(10, 25));
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        fizz.subscribe();
        subscriber.awaitTerminalEvent();
        List<String> onNextEvents = subscriber.getOnNextEvents();

        List<String> expected = Lists.newArrayList("Buzz", "11", "12", "13", "14", "Buzz", "16", "17", "18", "19", "Buzz");
        assertThat(onNextEvents).isEqualTo(expected);
    }

    @Test
    public void fizzBuzz() {
        Observable<String> fizz = fizzBuzz.fizzBuzz(Observable.range(10, 15));
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        fizz.subscribe(subscriber);
        subscriber.awaitTerminalEvent();
        List<String> onNextEvents = subscriber.getOnNextEvents();

        List<String> expected = Lists.newArrayList("Buzz", "11", "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz");
        assertThat(onNextEvents).isEqualTo(expected);
    }
}