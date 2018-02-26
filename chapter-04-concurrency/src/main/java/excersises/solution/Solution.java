package excersises.solution;

import common.DataProcessor;
import common.DiskReader;
import excersises.Exercises;
import rx.Observable;

import static common.SchedulerFactory.*;

public class Solution extends Exercises {

    public Observable<String> readDiskInOrder(Observable<Integer> sectors) {
        return sectors.concatMap(DiskReader::read);
    }

    public Observable<String> readDiskConcurrently(Observable<Integer> sectors) {
        return sectors.flatMap(i -> DiskReader.read(i).subscribeOn(diskScheduler));
    }

    public Observable<String> readDiskConcurrentlyButStoreInUiThread(Observable<Integer> sectors) {
        return readDiskConcurrently(sectors).observeOn(uiScheduler);
    }

    public Observable<String> readDiskInOrderWithProcessing(Observable<Integer> sectors) {
        return sectors.concatMap(DiskReader::read).compose(DataProcessor.processData());
    }

    public Observable<String> readDiskConcurrentlyWithProcessingInComputationThreadAndStoreInUiThread(Observable<Integer> sectors) {
        return sectors.flatMap(i -> DiskReader.read(i).subscribeOn(diskScheduler).observeOn(computationScheduler).compose(DataProcessor.processData()).observeOn(uiScheduler));
    }

}
