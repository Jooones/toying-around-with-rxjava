package excercise3_2;

import rx.Observable;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static rx.Observable.just;

public class MapTypes {

    private static final int TWO_THOUSAND = 2000;

    /**
     * In which order will the items emitted by getObservable() be printed in each of the 3 cases?
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Output with map: ");
        getObservable()
                .map(integer -> just(integer + 1).delay(current().nextInt(0, TWO_THOUSAND), MILLISECONDS))
                .map(integerObservable -> integerObservable.toBlocking().first())
                .subscribe(System.out::println);

        SECONDS.sleep(5);

        /*########################################*/

        System.out.println("Output with flatmap: ");
        getObservable()
                .flatMap(integer -> just(integer+1)
                        .delay(current().nextInt(0, TWO_THOUSAND), MILLISECONDS))
                .subscribe(System.out::println);

        SECONDS.sleep(5);

        /*########################################*/

        System.out.println("Output with concatmap: ");
        getObservable()
                .concatMap(integer -> just(integer+1).delay(current().nextInt(0, TWO_THOUSAND), MILLISECONDS))
                .subscribe(System.out::println);

        SECONDS.sleep(10);

        /*########################################*/

    }

    private static Observable<Integer> getObservable() {
        return Observable.range(0, 5);
    }
}
