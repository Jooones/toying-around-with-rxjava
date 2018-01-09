package com.jooones.toying.around.rxjava.one;

import rx.Observable;

public class Observables {

    /**
     * Given two monotonously increasing observables, returns a monotonously increasing observable. Duplicates are kept.
     * @param one
     * @param two
     * @return
     */
    public static Observable<Integer> sortedMerge(Observable<Integer> one, Observable<Integer> two) {
        return Observable.just(1,0); // TODO
    }

}
