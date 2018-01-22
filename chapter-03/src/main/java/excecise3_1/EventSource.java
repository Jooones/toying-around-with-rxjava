package excecise3_1;

import rx.Observable;

import java.time.LocalTime;

public interface EventSource {


    Observable<Event> getScheduledEventsForInterval(LocalTime schedulingIntervalStart);
}
