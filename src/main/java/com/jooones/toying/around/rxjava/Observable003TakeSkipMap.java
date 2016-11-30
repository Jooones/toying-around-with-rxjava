package com.jooones.toying.around.rxjava;

import rx.Observable;

public class Observable003TakeSkipMap {

    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9");

        observable
                .take(6)
                .skip(2)
                .map(s -> s + " up")
                .subscribe(System.out::println);
    }

}
