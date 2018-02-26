package excersises;

import common.DataProcessor;
import common.DiskReader;
import rx.Observable;

import static common.SchedulerFactory.computationScheduler;
import static common.SchedulerFactory.diskScheduler;
import static common.SchedulerFactory.uiScheduler;

public class Exercises {

    public Observable<String> readDiskInOrder(Observable<Integer> sectors) {
        return Observable.empty();
    }

    public Observable<String> readDiskConcurrently(Observable<Integer> sectors) {
        return Observable.empty();
    }

    public Observable<String> readDiskConcurrentlyButStoreInUiThread(Observable<Integer> sectors) {
        return Observable.empty();
    }

    public Observable<String> readDiskInOrderWithProcessing(Observable<Integer> sectors) {
        return Observable.empty();
    }

    public Observable<String> readDiskConcurrentlyWithProcessingInComputationThreadAndStoreInUiThread(Observable<Integer> sectors) {
        return Observable.empty();
    }

    // Calculates the greatest common divider
    // We can efficiently compute the gcd using the following property, which holds for positive integers p and q:
    // If p > q, the gcd of p and q is the same as the gcd of q and p % q.
    public int gcd(int p, int q) {
        if(p < q) {
            int r = p;
            p = q;
            q = r;
        }
        if(q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    public int gcdUsingATrampoline(int p, int q) {
        return 0;
    }

}
