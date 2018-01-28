package excercise3_3;

import rx.Observable;

import java.time.LocalTime;

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
