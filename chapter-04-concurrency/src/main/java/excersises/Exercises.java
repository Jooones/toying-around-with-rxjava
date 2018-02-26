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

}
