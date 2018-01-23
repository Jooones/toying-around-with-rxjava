package excercise3_2;

import org.junit.Test;
import rx.Observable;
import rx.observers.Subscribers;
import rx.observers.TestSubscriber;

import java.time.LocalTime;

import static java.time.LocalTime.now;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static rx.Observable.just;


public class EventSchedulerTest {

    @Test
    public void scheduleEventsForIntervals() {
        EventSource eventSource = schedulingIntervalStart -> just(schedulingIntervalStart.minusSeconds(4),
                schedulingIntervalStart.minusSeconds(2),
                schedulingIntervalStart.plusSeconds(1))
                .map(Event::new);

        EventScheduler eventScheduler = new EventScheduler(eventSource);
        Observable<LocalTime> intervals = just(now(),
                now().plusSeconds(10),
                now().plusSeconds(20));


        TestSubscriber<Event> subscriber = new TestSubscriber<>(Subscribers.create(event -> {
            event.executeEvent();
            System.out.println("Successfully executed scheduled event planned for: " + event.getPlannedTime());
        }));
        eventScheduler.scheduleEventsForIntervals(intervals)
                .subscribe(subscriber);
        subscriber.awaitTerminalEvent();


        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(9);
    }
}