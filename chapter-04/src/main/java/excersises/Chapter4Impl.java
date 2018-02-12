package excersises;

import common.PaginatedSource;
import rx.Observable;

public class Chapter4Impl implements Chapter4 {

    protected final PaginatedSource source;

    public Chapter4Impl(PaginatedSource source) {
        this.source = source;
    }

    @Override
    public Observable<Integer> getSinglePage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPageSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Integer> gracefullyHandleErrorWhenRequestingFirst3Pages() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Integer> getAllPagesUntilEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Integer> transformNumbers(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Observable<Boolean> pollUntilUpdatesAvailable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Integer> getAllPagesWhenUpdateAvailable() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Observable<Integer> getOnlyUpdatedvalues() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Observable<Integer> onlyTransfromUpdatedValues(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }

}
