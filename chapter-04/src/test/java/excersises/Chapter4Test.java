package excersises;

import common.PaginatedSourceImpl;
import excersises.solution.Chapter4Solution;
import org.junit.Test;
import rx.observers.AssertableSubscriber;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class Chapter4Test {

    private static final int MAX_PAGE = 5;
    private static final int PAGE_SIZE = 10;
    private static final int UPDATE_THRESHOLD = 4;
    private static final Set<Integer> UPDATED_VALUES = new HashSet<>(asList(7, 23, 44));

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
}