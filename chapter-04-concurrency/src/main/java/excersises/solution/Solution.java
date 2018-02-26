package excersises.solution;

import common.DataProcessor;
import common.DiskReader;
import excersises.Exercises;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

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

    public int gcdUsingATrampoline(int p, int q) {
        Scheduler.Worker worker = Schedulers.trampoline().createWorker();

        final int[] pVal = new int[] {p};
        final int[] qVal = new int[] {q};

        if(p < q) {
            pVal[0] = q;
            qVal[0] = p;
        }

        final rx.functions.Action0[] gcd = new Action0[] {null};
        gcd[0] = () -> {
            if(qVal[0] > 0) {
                int newQ = pVal[0] % qVal[0];
                pVal[0] = qVal[0];
                qVal[0] = newQ;
                worker.schedule(gcd[0]);
            }
        };
        worker.schedule(gcd[0]);

        return pVal[0];
    }

}
