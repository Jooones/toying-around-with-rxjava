package common.exercises;


import common.TcpResource;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Exercises {


    /**
     * Performa a request and time out if no events are emitted in one second
     */
    public Observable<String> performRequestAndTimeoutAfterOneSecondOfNoItems(TcpResource resource, String URI) {
        return resource.performRequest(URI)
                .timeout(1, TimeUnit.SECONDS);
    }

    /**
     * Performa a request and time out if not all items have been emitted after 5 seconds
     */
    public Observable<String> performRequestAndTimeoutIfDoesntCompleteInFiveSeconds(TcpResource resource, String URI) {
        return resource.performRequest(URI)
    }

}
