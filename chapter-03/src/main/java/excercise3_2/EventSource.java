package excercise3_2;

import rx.Observable;

import java.time.LocalTime;

public interface EventSource {


    Observable<Event> getScheduledEventsForInterval(LocalTime schedulingIntervalStart);
}
