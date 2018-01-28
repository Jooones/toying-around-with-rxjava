package excercise3_2;


import rx.Observable;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static rx.Observable.just;

public class MapTypes {


    private static final int STREAM_SIZE = 5;
    private static final int DELAY = 2000;

    /**
     * In which order will the items emitted by getObservable() be printed in each of the 3 cases?
     */
    public static void main(String[] args) {
        System.out.println("Output with map: ");
        getObservable()
                .map(integer -> just(integer + 1).delay(current().nextInt(0, DELAY), MILLISECONDS))
                .map(integerObservable -> integerObservable.toBlocking().first())
                .toBlocking()
                .subscribe(System.out::println);

        /*########################################*/

        System.out.println("Output with flatmap: ");
        getObservable()
                .flatMap(integer -> just(integer+1)
                        .delay(current().nextInt(0, DELAY), MILLISECONDS))
                .toBlocking()
                .subscribe(System.out::println);

        /*########################################*/

        System.out.println("Output with concatmap: ");
        getObservable()
                .concatMap(integer -> just(integer+1)
                        .delay(current().nextInt(0, DELAY), MILLISECONDS))
                .toBlocking()
                .subscribe(System.out::println);

        /*########################################*/

    }

    private static Observable<Integer> getObservable(){
        return Observable.range(0, STREAM_SIZE);
    }


}
