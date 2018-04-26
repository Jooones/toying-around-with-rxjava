package common;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.time.LocalTime;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TcpTestResource implements TcpResource {


    private int count = 5;

    private long errorTime = 5;

    @Override
    public Observable<String> performRequest(String URI, Scheduler scheduler) {
        switch (URI) {
            case "timingOutRequest" :
                return performRequestWithPotentialTimeout(count, scheduler);
            case "failingRequest" :
                return performRequestWithPotentialFailure(errorTime, scheduler);

            default: return Observable.empty();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(long errorTime) {
        this.errorTime = errorTime;
    }

    //Delays between events: 550, 650, 750, 850, 950, 1050, 1150

    //Total delays: 550, 1200, 1950, 2800, 3750, 4800, 5950
    private Observable<String> performRequestWithPotentialTimeout(int count, Scheduler scheduler) {
        return Observable.<Integer>range(1, count, scheduler)
                .concatMap(integer -> Observable.timer(550 + (integer-1)*100, MILLISECONDS, scheduler).map(aLong -> integer))
                .map(Object::toString);
    }

    private Observable<String> performRequestWithPotentialFailure(long i, Scheduler scheduler) {
        return Observable.just(scheduler.now())
                .map(s -> returnResponseIfEnoughTimePassedOrThrowError(i, scheduler, s));
    }

    private String returnResponseIfEnoughTimePassedOrThrowError(long i, Scheduler scheduler, Long s) {
        if(enoughTimePassed(i, scheduler, s)){
            return "1";
        } else {
            throw new RuntimeException("Request failed");
        }
    }

    private boolean enoughTimePassed(long i, Scheduler scheduler, Long s) {
        return scheduler.now() > (s + i);
    }


}
