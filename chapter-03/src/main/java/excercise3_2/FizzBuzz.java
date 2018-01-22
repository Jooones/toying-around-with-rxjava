package excercise3_2;


import rx.Observable;

public class FizzBuzz {

    //PART 1

    /**
     * Ex1a: write an observable that emits its input as a string except when the input is divisible by three,
     * in that case, it emits "Fizz"
     */
    public Observable<String> fizz(Observable<Integer> input) {
        return Observable.empty();
    }

    /**
     * Ex1a: write an observable that emits its input as a string except when the input is divisible by five,
     * in that case, it emits "Buzz"
     */
    public Observable<String> buzz(Observable<Integer> input) {
        return Observable.empty();
    }

    //------------------------------------------------------------------------------------------


    //PART 2

    /**
     * Ex1b: combine the previous exercises
     */
    public Observable<String> fizzBuzz(Observable<Integer> input) {
        return Observable.empty();
    }




}
