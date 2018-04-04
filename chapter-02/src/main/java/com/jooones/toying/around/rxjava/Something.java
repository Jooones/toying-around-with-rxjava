package com.jooones.toying.around.rxjava;

import rx.Observable;
import rx.observables.ConnectableObservable;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Something {

    public static void main(String[] args) throws InterruptedException {
//        Observable<String> observable = Observable.create(s -> s.onNext("Hello?!"));
//        observable.subscribe(System.out::println);
//        Observable<Object> observable1 = Observable.create(
//                subscriber -> {
//                    System.out.println("Create");
//                    subscriber.onNext("Hello");
//                }
//        ).cache();

        Observable<Integer> observable =
                Observable
                        .range(0, 100)
                        .cache()
                        .map(integer -> {
            System.out.println("something "+integer);
            return integer;
        });

        observable = observable.publish();
        observable.subscribe(o -> System.out.println(o));
        SECONDS.sleep(1);
        observable.subscribe(o -> System.out.println(o));
    }
}
