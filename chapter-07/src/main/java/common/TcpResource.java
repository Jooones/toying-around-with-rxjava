package common;

import rx.Observable;
import rx.Scheduler;

public interface TcpResource {
    Observable<String> performRequest(String URI, Scheduler scheduler);
}
