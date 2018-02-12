package common;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;

public class PaginatedSourceImpl implements PaginatedSource {

    private final int maxPage;
    private final int pageSize;
    private final int updateThreshold;
    private final Set<Integer> updatedValues;

    private int errorPage = -1;

    private AtomicInteger updatePollCounter = new AtomicInteger();
    private AtomicInteger getPageCounter = new AtomicInteger();
    private boolean hasUpdates;

    private boolean pageRequestedBeforeUpdatesAvailable = false;

    public PaginatedSourceImpl(int maxPage, int pageSize, int updateThreshold, Set<Integer> updatedValues) {
        this.maxPage = maxPage;
        this.pageSize = pageSize;
        this.updateThreshold = updateThreshold;
        this.updatedValues = new HashSet<>(updatedValues);
    }

    @Override
    public void getPage(int pageNumber, Consumer<List<Integer>> callback, Consumer<Exception> errorCallback) {
        System.out.println("Requested page number " + pageNumber);
        getPageCounter.incrementAndGet();
        if(updatePollCounter.get() <= updateThreshold) {
            pageRequestedBeforeUpdatesAvailable = true;
        }

        try {
            checkPageNumber(pageNumber);

            hasUpdates = false;
            if(pageNumber < maxPage) {
                callback.accept(createPage(pageNumber));
            }
            callback.accept(emptyList());
        } catch (Exception e) {
            errorCallback.accept(e);
        }
    }

    private void checkPageNumber(int pageNumber) {
        if (pageNumber < 0 || pageNumber > maxPage) {
            IndexOutOfBoundsException exception = new IndexOutOfBoundsException("Invalid page number requested");
            exception.printStackTrace();
            throw exception;
        }
        if(pageNumber == errorPage) {
            IllegalStateException exception = new IllegalStateException("Encountered an error generating page " + pageNumber);
            exception.printStackTrace();
            throw exception;
        }
    }

    public List<Integer> createPage(int pageNumber) {
        return IntStream.range(pageNumber, pageSize + pageNumber)
                .map(number -> number + pageSize * pageNumber - pageNumber)
                .map(number -> {
                    if (updatePollCounter.get() > updateThreshold && updatedValues.contains(number)) {
                        return number * 1000;
                    }
                    return number;
                })
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasUpdates() {
        if(updatePollCounter.getAndIncrement() == updateThreshold) {
            hasUpdates = true;
        }
        System.out.println(hasUpdates ? "Updates available" : "No updates available");
        return hasUpdates;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getUpdateThreshold() {
        return updateThreshold;
    }

    public int getUpdatePollCounter() {
        return updatePollCounter.get();
    }

    public int getGetPageCounter() {
        return getPageCounter.get();
    }

    public boolean isHasUpdates() {
        return hasUpdates;
    }

    public boolean isPageRequestedBeforeUpdatesAvailable() {
        return pageRequestedBeforeUpdatesAvailable;
    }

    public int getErrorPage() {
        return errorPage;
    }

    public PaginatedSourceImpl setErrorPage(int errorPage) {
        this.errorPage = errorPage;
        return this;
    }
}
