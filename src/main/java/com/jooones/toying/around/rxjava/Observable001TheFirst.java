package com.jooones.toying.around.rxjava;

import rx.Observable;

public class Observable001TheFirst {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(s -> {
            s.onNext("Hello?!");
            s.onNext("Is it me you are looking for?");
            s.onCompleted();
        });

        observable.subscribe(System.out::println);


        System.out.println("-----------");


        Observable<String> observable2 = Observable.just("Buy", "Use", "Break", "Fix");

        observable2.subscribe(s -> System.out.println(s + " it"));
        observable2.subscribe(s -> System.out.println(s + " nothing!"));
    }

}
