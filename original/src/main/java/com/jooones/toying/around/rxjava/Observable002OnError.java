package com.jooones.toying.around.rxjava;

import rx.Observable;

public class Observable002OnError {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(s -> {
            s.onNext("One");
            s.onNext("Two");
            s.onNext("Three");
            throw new RuntimeException();
        });

        Observable<String> observable2 = Observable.create(s -> {
            s.onNext("Lets a Go!");
        });

        observable
                .onErrorResumeNext(observable2)
                .subscribe(System.out::println);
    }

}
