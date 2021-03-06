package excercise3_3.solution;

import excercise3_3.Event;
import excercise3_3.EventSource;
import rx.Observable;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static java.time.LocalTime.*;

public class SchedulerSolution {

    private final EventSource eventSource;

    public SchedulerSolution(EventSource eventSource){

        this.eventSource = eventSource;
    }

    public static int TOLERANCE_IN_SECONDS = 10;

    public Observable<Event> scheduleEventsForIntervals(Observable<LocalTime> intervalStarts) {
        return intervalStarts.flatMap(intervalStart -> {
                    Observable<Event> scheduledEventsForInterval = eventSource.getScheduledEventsForInterval(intervalStart);
                    return scheduledEventsForInterval
                            .filter(event -> event.getPlannedTime().isAfter(intervalStart))
                            .delay(5, TimeUnit.SECONDS)
                            .mergeWith(scheduledEventsForInterval.filter(event -> !event.getPlannedTime().isAfter(intervalStart)))
                            .delay(Duration.between(now(), intervalStart).getSeconds(), TimeUnit.SECONDS);

                }
        );
    }

}