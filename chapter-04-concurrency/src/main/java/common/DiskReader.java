package common;

import rx.Observable;

//0 2a
//200 0a
//300 1a
//400 2b
//500 1b
//600 0b
//700 1c
//800 2c
//1000 0c
public class DiskReader {

    public static Observable<String> read(int sector) {
        switch (sector) {
            case 0:
                return createSectorObservable(300, 400, new String[] {"0a", "0b", "0c"});
            case 1:
                return createSectorObservable(400, 200, new String[] {"1a", "1b", "1c"});
            case 2:
                return createSectorObservable(100, 400, new String[] {"2a", "2b", "2c"});
            default:
                return Observable.empty();
        }
    }

    private static Observable<String> createSectorObservable(long seekTimeMillis, long sectorReadTimeMillis, String[] data) {
        return Observable.create(subscriber -> {
            try {
                Thread.sleep(seekTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext(data[0]);
            try {
                Thread.sleep(sectorReadTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext(data[1]);
            try {
                Thread.sleep(sectorReadTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext(data[2]);
            subscriber.onCompleted();
        });
    }

}
