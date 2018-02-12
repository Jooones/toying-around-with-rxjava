package excersises.solution;


import common.PaginatedSource;
import excersises.Chapter4;
import rx.Observable;

import java.util.List;

public class Chapter4Solution implements Chapter4 {

    private final PaginatedSource source;

    public Chapter4Solution(PaginatedSource source) {
        this.source = source;
    }

    @Override
    public Observable<Integer> getSinglePage() {
        return getPage(0);
    }

    @Override
    public int getPageSize() {
        return getSinglePage().count().toBlocking().single();
    }

    @Override
    public Observable<Integer> gracefullyHandleErrorWhenRequestingFirst3Pages() {
        return Observable.range(0, 3)
                .flatMap(num -> getPage(num).doOnError(e -> e.printStackTrace()).onErrorResumeNext(Observable.empty()));
    }

    @Override
    public Observable<Integer> getAllPagesUntilEmpty() {
        return getNextPage(0).doOnNext(number -> System.out.println(number));
    }

    @Override
    public Observable<Integer> transformNumbers(Observable<Integer> input) {
        return input.zipWith(
                getAllPagesUntilEmpty(),
                (number1, number2) -> number1 * number2
        );
    }


    @Override
    public Observable<Boolean> pollUntilUpdatesAvailable() {
        return Observable.fromCallable(() -> source.hasUpdates())
                .repeat()
                .takeWhile(updates -> !updates);
    }

    @Override
    public Observable<Integer> getAllPagesWhenUpdateAvailable() {
        return pollUntilUpdatesAvailable().concatMap(updates -> getAllPagesUntilEmpty());

    }

    @Override
    public Observable<Integer> getOnlyUpdatedvalues() {
        Observable<Integer> original = getAllPagesUntilEmpty().cache();
        return original.last().concatMap(last ->
                pollUntilUpdatesAvailable()
                        .concatMap(updates ->
                                getAllPagesUntilEmpty()
                                        .flatMap(number -> original.contains(number)
                                                .flatMap(contains -> contains ? Observable.empty() : Observable.just(number)))));

    }

    @Override
    public Observable<Integer> onlyTransfromUpdatedValues(Observable<Integer> input) {
        throw new UnsupportedOperationException();
    }

    private Observable<Integer> getNextPage(int pageNr) {
        Observable<Integer> page = getPage(pageNr).cache();
        return page.concatWith(page.isEmpty().flatMap(isEmpty -> isEmpty ? Observable.empty() : getNextPage(pageNr + 1)));
    }

    private Observable<Integer> getPage(int i) {
        return Observable.<List<Integer>>create(subscriber -> {
            source.getPage(i, list -> {
                subscriber.onNext(list);
                subscriber.onCompleted();
            }, subscriber::onError);
        }).flatMap(Observable::from);
    }
}
