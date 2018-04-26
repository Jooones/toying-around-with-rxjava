package exercises;


import common.TcpResource;
import rx.Observable;
import rx.Scheduler;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Exercises {


    /**
     * Perform a request and time out if no events are emitted in one second
     */
    public Observable<String> performRequestAndTimeoutAfterOneSecondOfNoItems(TcpResource resource, String URI, Scheduler scheduler) {
        return resource.performRequest(URI, scheduler);
    }

    /**
     * Perform a request and time out if not all items have been emitted after 5 seconds
     */
    public Observable<String> performRequestAndTimeoutIfDoesntCompleteInFiveSeconds(TcpResource resource, String URI, Scheduler scheduler) {
        return resource.performRequest(URI, scheduler);
    }

    /**
     * Perform a request and retry with exponential back-off
     */
    public Observable<String> performRequestAndRetryOnFailure(TcpResource resource, String URI, Scheduler scheduler) {
        return resource.performRequest(URI, scheduler);
    }

    /**
     * Perform a request, retry with exponential back-off on failure. If nothing is recieved after 5 seconds, timeout and return an error message
     */
    public Observable<String> performRequestAndRetryOnFailureWithTimeOut(TcpResource resource, String URI, Scheduler scheduler) {
        return resource.performRequest(URI, scheduler);
    }









}
