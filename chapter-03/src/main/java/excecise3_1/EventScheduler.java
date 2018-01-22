package excecise3_1;

import rx.Observable;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static java.time.LocalTime.*;

public class EventScheduler {

    private final EventSource eventSource;

    EventScheduler(EventSource eventSource){

        this.eventSource = eventSource;
    }

    static int TOLERANCE_IN_SECONDS = 10;

    public Observable<Event> scheduleEventsForIntervals(Observable<LocalTime> intervalStarts) {
        return Observable.empty();
    }

}
