package excersises;

import common.Logger;
import excersises.solution.Solution;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;

public class ExercisesTest {

    private Exercises exercises = new Solution();

    @Before
    public void setup() {
        Logger.clear();
    }

    @Test
    public void readDiskInOrder() throws Exception {
        boolean[] wait = new boolean[] {true};
        Observable<String> diskReader = exercises.readDiskInOrder(Observable.range(0,5));
        diskReader.subscribe(Logger::log, t -> t.printStackTrace(), () -> wait[0] = false);

        while (wait[0]) Thread.sleep(100);

        assertThat(Logger.messages).containsExactly(Pair.of("main", "0a"), Pair.of("main", "0b"), Pair.of("main", "0c"), Pair.of("main", "1a"), Pair.of("main", "1b"), Pair.of("main", "1c"), Pair.of("main", "2a"), Pair.of("main", "2b"), Pair.of("main", "2c"));
    }

    @Test
    public void readDiskConcurrently() throws Exception {
        boolean[] wait = new boolean[] {true};
        Observable<String> diskReader = exercises.readDiskConcurrently(Observable.range(0,5));
        diskReader.subscribe(Logger::log, t -> t.printStackTrace(), () -> wait[0] = false);

        while (wait[0]) Thread.sleep(100);

        assertThat(Logger.messages).containsExactly(Pair.of("Disk", "2a"), Pair.of("Disk", "0a"), Pair.of("Disk", "1a"), Pair.of("Disk", "2b"), Pair.of("Disk", "1b"), Pair.of("Disk", "0b"), Pair.of("Disk", "1c"), Pair.of("Disk", "2c"), Pair.of("Disk", "0c"));
    }

    @Test
    public void readDiskConcurrentlyButStoreInUiThread() throws Exception {
        boolean[] wait = new boolean[] {true};
        Observable<String> diskReader = exercises.readDiskConcurrentlyButStoreInUiThread(Observable.range(0,5));
        diskReader.subscribe(Logger::log, t -> t.printStackTrace(), () -> wait[0] = false);

        while (wait[0]) Thread.sleep(100);

        assertThat(Logger.messages).containsExactly(Pair.of("UI_Thread", "2a"), Pair.of("UI_Thread", "0a"), Pair.of("UI_Thread", "1a"), Pair.of("UI_Thread", "2b"), Pair.of("UI_Thread", "1b"), Pair.of("UI_Thread", "0b"), Pair.of("UI_Thread", "1c"), Pair.of("UI_Thread", "2c"), Pair.of("UI_Thread", "0c"));
    }

    @Test
    public void readDiskInOrderWithProcessing() throws Exception {
        boolean[] wait = new boolean[] {true};
        Observable<String> diskReader = exercises.readDiskInOrderWithProcessing(Observable.range(0,5));
        diskReader.subscribe(Logger::log, t -> t.printStackTrace(), () -> wait[0] = false);

        while (wait[0]) Thread.sleep(100);

        assertThat(Logger.messages).containsExactly(Pair.of("main", "Processing:0a"), Pair.of("main", "0a"), Pair.of("main", "Processing:0b"), Pair.of("main", "0b"), Pair.of("main", "Processing:0c"), Pair.of("main", "0c"), Pair.of("main", "Processing:1a"), Pair.of("main", "1a"), Pair.of("main", "Processing:1b"), Pair.of("main", "1b"), Pair.of("main", "Processing:1c"), Pair.of("main", "1c"), Pair.of("main", "Processing:2a"), Pair.of("main", "2a"), Pair.of("main", "Processing:2b"), Pair.of("main", "2b"), Pair.of("main", "Processing:2c"), Pair.of("main", "2c"));
    }

    @Test
    public void readDiskConcurrentlyWithProcessingInComputationThreadAndStoreInUiThread() throws Exception {
        boolean[] wait = new boolean[] {true};
        Observable<String> diskReader = exercises.readDiskConcurrentlyWithProcessingInComputationThreadAndStoreInUiThread(Observable.range(0,5));
        diskReader.subscribe(Logger::log, t -> t.printStackTrace(), () -> wait[0] = false);

        while (wait[0]) Thread.sleep(100);

        assertThat(Logger.messages).containsExactly(Pair.of("Comp", "Processing:2a"), Pair.of("UI_Thread", "2a"), Pair.of("Comp", "Processing:0a"), Pair.of("UI_Thread", "0a"), Pair.of("Comp", "Processing:1a"), Pair.of("UI_Thread", "1a"), Pair.of("Comp", "Processing:2b"), Pair.of("Comp", "Processing:1b"), Pair.of("UI_Thread", "2b"), Pair.of("Comp", "Processing:0b"), Pair.of("UI_Thread", "1b"), Pair.of("Comp", "Processing:1c"), Pair.of("UI_Thread", "0b"), Pair.of("Comp", "Processing:2c"), Pair.of("UI_Thread", "1c"), Pair.of("Comp", "Processing:0c"), Pair.of("UI_Thread", "2c"), Pair.of("UI_Thread", "0c"));
    }

}