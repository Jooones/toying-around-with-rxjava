package com.jooones.toying.around.rxjava.one;

import rx.Observable;

import java.util.Iterator;

public class MultiplesIterator implements Iterator<Integer> {
    private Integer current = 0;
    private Integer factor;

    public MultiplesIterator(int factor) {
        this.factor = factor;
    }

    public boolean hasNext() {
        return current<=Integer.MAX_VALUE-factor;
    }

    @Override
    public Integer next() {
        Integer result = current;
        current = current+factor;
        return result;
    }

    public static Observable<Integer> getObservable(MultiplesIterator iterator){
        return Observable.create(subscriber -> {
            System.out.println("Creation");
            while(iterator.hasNext()) {
                Integer i = iterator.next();
                subscriber.onNext(i);
            }

            System.out.println("Completed");
            subscriber.onCompleted();
        });
    }
}
