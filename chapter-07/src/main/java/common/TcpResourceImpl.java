package common;

import rx.Observable;

import java.time.LocalTime;

import static java.util.concurrent.TimeUnit.*;

public class TcpResourceImpl implements TcpResource {


    @Override
    public Observable<String> performRequest(String URI) {
        switch (URI) {
            case "a" :  return performRequestWithPotentialTimeout(5);

            default: return Observable.empty();
        }
    }

    private Observable<String> performRequestWithPotentialTimeout(int count) {
        return Observable.<Integer>range(1, count).delay(i -> {
            try {
                Thread.sleep(i*100 + 550);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } return Observable.empty();
        }).map(Object::toString);
    }

    public static void main(String[] args) {
        new TcpResourceImpl().performRequestWithPotentialTimeout(5).doOnNext(s ->
                System.out.println(LocalTime.now() + " " + s)).timeout(1, SECONDS).subscribe();
    }
}
