package com.jooones.toying.around.rxjava.one;

import org.junit.Test;
import rx.Observable;
import rx.observers.AssertableSubscriber;

import static org.junit.Assert.*;

public class MultiplesIteratorTest {

    @Test
    public void getObservable() throws Exception {
        Observable<Integer> observable = MultiplesIterator.getObservable(new MultiplesIterator(5));
        System.out.println("Let's subscribe");
        observable.take(5).subscribe(System.out::println);
    }

}