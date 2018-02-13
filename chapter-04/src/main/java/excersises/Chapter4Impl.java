package excersises;

import common.PaginatedSource;
import rx.Observable;

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
        throw new UnsupportedOperationException();
    }

    /**
     * @return The size of the first page
     */
    @Override
    public int getPageSize() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return The first 3 pages, allowing for errors, and continuing.
     */
    @Override
    public Observable<Integer> gracefullyHandleErrorWhenRequestingFirst3Pages() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return All pages, until an empty page is returned?
     */
    @Override
    public Observable<Integer> getAllPagesUntilEmpty() {
        throw new UnsupportedOperationException();
    }

    /**
     * Transform all numbers from the source by multiplying them with the corresponding item in the input stream.
     * @param input An Observable with the number to be used for the multiplication
     * @return The stream of transformed numbers
     */
    @Override
    public Observable<Integer> transformNumbers(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }


    /**
     * Poll the synchronous hasUpdates method until it returns true
     * @return A stream that completes when the source has updates.
     */
    @Override
    public Observable<Boolean> pollUntilUpdatesAvailable() {
        throw new UnsupportedOperationException();
    }

    /**
     * Wait until updates are available before getting all the pages from the source
     * @return A stream of numbers returned by the source.
     */
    @Override
    public Observable<Integer> getAllPagesWhenUpdateAvailable() {
        throw new UnsupportedOperationException();

    }

    /**
     * Only return those numbers that were updated after the hasUpdates signals it has new values.
     * @return Stream of only the changed values
     */
    @Override
    public Observable<Integer> getOnlyUpdatedvalues() {
        throw new UnsupportedOperationException();

    }

    /**
     * Return the transformed(multiplied) values of only those values that were changed after the update
     * @param input An Observable with the number to be used for the multiplication
     * @return Stream of the modified and transformed numbers.
     */
    @Override
    public Observable<Integer> onlyTransfromUpdatedValues(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }

}
