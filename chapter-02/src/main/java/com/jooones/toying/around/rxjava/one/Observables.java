package com.jooones.toying.around.rxjava.one;

import rx.Observable;
import rx.schedulers.Schedulers;

public class Observables {

    /**
     * Given two monotonously increasing observables, returns a monotonously increasing observable. Duplicates are kept.
     *
     * @param one
     * @param two
     * @return
     */
    public static Observable<Integer> sortedMerge(Observable<Integer> one, Observable<Integer> two) {
        /*final Integer[] oneBuffer = new Integer[1];
        final Integer[] twoBuffer = new Integer[1];
        final Object mutexOne = new Object(), mutexTwo = new Object();
        return Observable.create(subscriber -> {
                    one.subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                        oneBuffer[0] = integer;
                        if (twoBuffer[0] == null) {
                            mutexTwo.notify();
                            try {
                                mutexOne.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        while (oneBuffer[0] != null) {
                            if (oneBuffer[0] < twoBuffer[0]) {
                                subscriber.onNext(oneBuffer[0]);
                                oneBuffer[0] = null;
                            } else {
                                subscriber.onNext(twoBuffer[0]);
                                twoBuffer[0] = null;
                                mutexTwo.notify();
                                try {
                                    mutexOne.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, throwable -> subscriber.onError(throwable));

                    two.subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                        try {
                            mutexTwo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        twoBuffer[0] = integer;
                        mutexOne.notify();
                    }, throwable -> subscriber.onError(throwable));
                }

        );*/

        return Observable.merge(one, two).sorted();
    }



}
