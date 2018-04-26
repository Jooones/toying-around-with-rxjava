package exercises;

import common.TcpResource;
import common.TcpTestResource;
import org.junit.Before;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

public class ExercisesTest {

    private TcpResource tcpResource;
    private TestSubscriber<String> testSubscriber;
    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        tcpResource = new TcpTestResource();
        testSubscriber = new TestSubscriber<>();
        testScheduler = new TestScheduler();
    }


}