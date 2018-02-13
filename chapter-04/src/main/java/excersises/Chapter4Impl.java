package excersises;

import common.PaginatedSource;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

public class Chapter4Impl implements Chapter4 {

    protected final PaginatedSource source;

    public Chapter4Impl(PaginatedSource source) {
        this.source = source;
    }

    /**
     * @return All the integers provided by the first page (0-index) as an Observable stream.
     */
    @Override
    public Observable<Integer> getSinglePage() {
        return getSinglePage(0);
    }

    private Observable<Integer> getSinglePage(int pageNumber) {
        return Observable.<List<Integer>>create(
                subscriber -> {
                    source.getPage(pageNumber, (list) -> {
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }, subscriber::onError);
                }
        ).flatMap(Observable::from)
                .doOnNext(System.out::println);
    }

    /**
     * @return The size of the first page
     */
    @Override
    public int getPageSize() {
        return getSinglePage().count().toBlocking().single();
    }

    /**
     * @return The first 3 pages, allowing for errors, and continuing.
     */
    @Override
    public Observable<Integer> gracefullyHandleErrorWhenRequestingFirst3Pages() {
        return getSinglePage(0)
                .onErrorResumeNext(Observable.empty())
                .concatWith(getSinglePage(1)
                        .onErrorResumeNext(Observable.empty()))
                .concatWith(getSinglePage(2)
                        .onErrorResumeNext(Observable.empty()));
    }

    /**
     * @return All pages, until an empty page is returned?
     */
    @Override
    public Observable<Integer> getAllPagesUntilEmpty() {
        return Observable.range(0, Integer.MAX_VALUE)
                .concatMap((i)-> getSinglePage(i).toList())
                .takeWhile(listObservable -> !listObservable.isEmpty())
                .flatMap(Observable::from);
    }

    /**
     * Transform all numbers from the source by multiplying them with the corresponding item in the input stream.
     *
     * @param input An Observable with the number to be used for the multiplication
     * @return The stream of transformed numbers
     */
    @Override
    public Observable<Integer> transformNumbers(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }


    /**
     * Poll the synchronous hasUpdates method until it returns true
     *
     * @return A stream that completes when the source has updates.
     */
    @Override
    public Observable<Boolean> pollUntilUpdatesAvailable() {
        throw new UnsupportedOperationException();
    }

    /**
     * Wait until updates are available before getting all the pages from the source
     *
     * @return A stream of numbers returned by the source.
     */
    @Override
    public Observable<Integer> getAllPagesWhenUpdateAvailable() {
        throw new UnsupportedOperationException();

    }

    /**
     * Only return those numbers that were updated after the hasUpdates signals it has new values.
     *
     * @return Stream of only the changed values
     */
    @Override
    public Observable<Integer> getOnlyUpdatedvalues() {
        throw new UnsupportedOperationException();

    }

    /**
     * Return the transformed(multiplied) values of only those values that were changed after the update
     *
     * @param input An Observable with the number to be used for the multiplication
     * @return Stream of the modified and transformed numbers.
     */
    @Override
    public Observable<Integer> onlyTransfromUpdatedValues(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }

}
