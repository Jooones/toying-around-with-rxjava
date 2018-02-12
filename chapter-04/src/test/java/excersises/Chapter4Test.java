package excersises;

import common.PaginatedSourceImpl;
import excersises.solution.Chapter4Solution;
import org.junit.Test;
import rx.Observable;
import rx.observers.AssertableSubscriber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static rx.Observable.from;
import static rx.Observable.just;

public class Chapter4Test {

    private static final int MAX_PAGE = 5;
    private static final int PAGE_SIZE = 10;
    private static final int UPDATE_THRESHOLD = 4;
    private static final Set<Integer> UPDATED_VALUES = new HashSet<>(asList(7, 23, 44));
    private static final List<Integer> RANDOM_VALUES = asList(196,138, 41,128,  9,164, 58,189, 74,118, 92, 34,162,117, 67,102,  1,177,151,198,153,155, 25,167,  4,  2,119, 73, 53,200,132, 27, 10, 38,178, 51, 57,133, 69,188,150,  7,116, 66,165,192, 49,108,107, 63,113, 68,  3,124,143);

    private PaginatedSourceImpl source = new PaginatedSourceImpl(MAX_PAGE, PAGE_SIZE, UPDATE_THRESHOLD, UPDATED_VALUES);

    private Chapter4 chapter4 = new Chapter4Solution(source);

    @Test
    public void getSinglePage() {
        AssertableSubscriber<Integer> subscriber = chapter4.getSinglePage().test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValueCount(PAGE_SIZE);
        subscriber.assertValues(IntStream.range(0, PAGE_SIZE).boxed().toArray(Integer[]::new));
    }

    @Test
    public void getPageSize() {
        assertThat(chapter4.getPageSize()).isEqualTo(PAGE_SIZE);
    }

    @Test
    public void gracefullyHandleErrorWhenRequestingFirst3Pages() {
        source.setErrorPage(1);

        AssertableSubscriber<Integer> subscriber = chapter4.gracefullyHandleErrorWhenRequestingFirst3Pages().test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValueCount(PAGE_SIZE * 2);
        subscriber.assertValues(IntStream.concat(
                IntStream.range(0, 10),
                IntStream.range(20, 30))
                .boxed().toArray(Integer[]::new));
        assertThat(source.getGetPageCounter()).isEqualTo(3);
    }

    @Test
    public void getAllPagesUntilEmpty() {
        AssertableSubscriber<Integer> subscriber = chapter4.getAllPagesUntilEmpty().test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValueCount(PAGE_SIZE * MAX_PAGE);
        subscriber.assertValues(IntStream.range(0, PAGE_SIZE * MAX_PAGE).boxed().toArray(Integer[]::new));
        assertThat(source.getGetPageCounter()).isEqualTo(MAX_PAGE + 1);
    }

    @Test
    public void transformNumbers() {
        AssertableSubscriber<Integer> subscriber = chapter4.transformNumbers(from(RANDOM_VALUES)).test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValueCount(50);
        subscriber.assertValues(IntStream.range(0, MAX_PAGE*PAGE_SIZE)
                .map(number -> RANDOM_VALUES.get(number) * number).boxed().toArray(Integer[]::new));
        assertThat(source.getGetPageCounter()).isEqualTo(6);
    }

    @Test
    public void pollUntilUpdatesAvailable() {
        AssertableSubscriber<Boolean> subscriber = chapter4.pollUntilUpdatesAvailable().test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();

        assertThat(source.getUpdatePollCounter()).isEqualTo(UPDATE_THRESHOLD + 1);
    }

    @Test
    public void getAllPagesWhenUpdateAvailable() {
        AssertableSubscriber<Integer> subscriber = chapter4.getAllPagesWhenUpdateAvailable().test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();

        assertThat(source.getUpdatePollCounter()).isEqualTo(UPDATE_THRESHOLD + 1);
        subscriber.assertValueCount(PAGE_SIZE * MAX_PAGE);
        assertThat(source.getGetPageCounter()).isEqualTo(MAX_PAGE + 1);
        assertThat(source.isPageRequestedBeforeUpdatesAvailable()).isFalse();
        subscriber.assertValues(IntStream.range(0, PAGE_SIZE * MAX_PAGE)
                .map(number -> UPDATED_VALUES.contains(number) ? number * 1_000 : number)
                .boxed().toArray(Integer[]::new));
    }

    @Test
    public void getOnlyUpdatedvalues() {
        AssertableSubscriber<Integer> subscriber = chapter4.getOnlyUpdatedvalues().test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValueCount(UPDATED_VALUES.size());
        subscriber.assertValues(UPDATED_VALUES.stream().mapToInt(number -> number * 1_000).boxed().toArray(Integer[]::new));
        assertThat(source.getGetPageCounter()).isEqualTo((MAX_PAGE + 1)*2);
    }

    @Test
    public void onlyTransfromUpdatedValues() {
        AssertableSubscriber<Integer> subscriber = chapter4.onlyTransfromUpdatedValues(from(RANDOM_VALUES)).test();

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValueCount(UPDATED_VALUES.size());
        subscriber.assertValues(UPDATED_VALUES.stream().mapToInt(number -> number * 1_000).boxed().toArray(Integer[]::new));
        assertThat(source.getGetPageCounter()).isEqualTo((MAX_PAGE + 1)*2);
    }
}