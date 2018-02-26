package common;

import rx.Observable;

public class DataProcessor {

    public static Observable.Transformer<String, String> processData() {
        return upstream -> upstream.map(s -> {
            long executionTime = 75L * (s.toCharArray()[1] - 96);
            Logger.log("Processing:" + s);
            try {
                Thread.sleep(executionTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        });
    }

}
