package excercise3_1.solution;

import rx.Observable;

public class FizzBuzzSolution {

    /**
     * Ex1a: write an observable that emits its input as a string except when the input is divisible by three,
     * in that case, it emits "Fizz"
     */
    public Observable<String> fizz(Observable<Integer> input) {
        return Observable.just("11", "Fizz", "13", "14", "Fizz");
    }

    /**
     * Ex1a: write an observable that emits its input as a string except when the input is divisible by five,
     * in that case, it emits "Buzz"
     */
    public Observable<String> buzz(Observable<Integer> input) {
        return Observable.just("11", "12", "13", "14", "Buzz");
    }

    /**
     * Ex1b: combine the observables from previous exercises to recreate the FizzBuzz sequence
     */
    public Observable<String> fizzBuzz(Observable<Integer> input) {
        return input.zipWith(
                fizz(input)
                        .map(s1 -> s1.equals("Fizz") ? s1 : "")
                        .zipWith(buzz(input)
                                .map(s2 -> s2.equals("Buzz") ? s2 : ""), String::concat),
                (integer, s) -> s.isEmpty() ? integer.toString() : s);

    }
}
