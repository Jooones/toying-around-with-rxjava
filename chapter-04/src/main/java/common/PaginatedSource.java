package common;

import java.util.List;
import java.util.function.Consumer;

public interface PaginatedSource {
    void getPage(int pageNumber, Consumer<List<Integer>> callback, Consumer<Exception> errorCallback);

    void resetValue(int originalValue, int newValue);

    boolean hasUpdates();
}
