package excersises;

import rx.Observable;

public interface Chapter4 {
    Observable<Integer> getSinglePage();

    int getPageSize();

    Observable<Integer> gracefullyHandleErrorWhenRequestingFirst3Pages();

    Observable<Integer> getAllPagesUntilEmpty();

    Observable<Integer> transformNumbers(Observable<Integer> input);

    Observable<Boolean> pollUntilUpdatesAvailable();

    Observable<Integer> getAllPagesWhenUpdateAvailable();

    Observable<Integer> getOnlyUpdatedvalues();

    Observable<Integer> onlyTransfromUpdatedValues(Observable<Integer> input);
}
