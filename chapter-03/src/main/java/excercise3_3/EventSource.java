package excercise3_3;

import rx.Observable;

import java.time.LocalTime;

public interface EventSource {


    Observable<Event> getScheduledEventsForInterval(LocalTime schedulingIntervalStart);
}
