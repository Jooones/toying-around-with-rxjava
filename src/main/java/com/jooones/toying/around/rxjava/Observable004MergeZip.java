package com.jooones.toying.around.rxjava;

import rx.Observable;

public class Observable004MergeZip {

    public static void main(String[] args) {
        Observable<String> o1 = Observable.just("1", "2", "3", "4");
        Observable<String> o2 = Observable.just("5", "6", "7", "8");

        Observable<String> merged = Observable.merge(o1, o2);

        merged.subscribe(System.out::println);


        Observable<String> zipped = Observable.zip(o1, o2, (x,y) -> x + y);

        zipped.subscribe(System.out::println);
    }

}
