package common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

public class PaginatedSourceImplTest {
    private static final int MAX_PAGE = 5;
    private static final int PAGE_SIZE = 10;
    private static final int UPDATE_THRESHOLD = 4;

    PaginatedSourceImpl source = new PaginatedSourceImpl(MAX_PAGE, PAGE_SIZE, UPDATE_THRESHOLD, emptySet());

    @Test
    public void getPage_GivenNegativePageNumber_ThenExceptionThrown() {
        AtomicReference<Exception> expected = new AtomicReference<>();
        source.getPage(-1, list -> {}, e -> expected.set(e));

        assertThat(expected.get()).isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void getPage_GivenToBigPageNumber_ThenExceptionThrown() {
        AtomicReference<Exception> expected = new AtomicReference<>();
        source.getPage(MAX_PAGE + 1, list -> {}, e -> expected.set(e));

        assertThat(expected.get()).isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }


    @Test
    public void getPage_GivenMaxPage_ThenEmptyListReturned() {
        List<Integer> result = new ArrayList<>();
        source.getPage(MAX_PAGE + 1, list -> result.addAll(list), e -> {});

        assertThat(result).isEmpty();
    }
}