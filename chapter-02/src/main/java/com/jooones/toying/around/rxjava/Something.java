package com.jooones.toying.around.rxjava;

import rx.Observable;

public class Something {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(s -> s.onNext("Hello?!"));
        observable.subscribe(System.out::println);
    }
}
