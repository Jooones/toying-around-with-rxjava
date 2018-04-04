package com.jooones.toying.around.rxjava.one;

import org.junit.Test;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class ObservablesTest {

    @Test
    public void sortedMerge() throws Exception {
        Integer[] one = {0,3,6};
        Integer[] two = {0,2,4};
        final Integer[] previous = {null};
        final Integer[] nbOfElementsEmitted = {0};

        int nbOfElements = 6;

        Observable<Integer> observableOne = MultiplesIterator.getObservable(new MultiplesIterator(300000));
        Observable<Integer> observableTwo = MultiplesIterator.getObservable(new MultiplesIterator(500000));

        Observables.sortedMerge(observableOne, observableTwo).take(nbOfElements).subscribe((i) -> {
            if(previous[0]!=null) {
                assertThat(i).isGreaterThanOrEqualTo(previous[0]);
                previous[0]=i;
            }
            nbOfElementsEmitted[0]++;
        },
        (error) -> fail(error.getMessage()),
        () -> {
            assertThat(nbOfElementsEmitted[0]).isEqualTo(nbOfElements);
            assertThat(observableOne.count().toBlocking().first()+observableTwo.count().toBlocking().first()).isEqualTo(nbOfElementsEmitted[0]);
        });
    }

}