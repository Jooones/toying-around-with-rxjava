package common;


import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Logger {

    private static long start = System.currentTimeMillis();

    public static final Collection<Pair<String, String>> messages = Collections.synchronizedCollection(new ArrayList<>());

    public static void log(Object message) {
        String threadName = Thread.currentThread().getName();
        String threadNameWithoutNumber = threadName.split("-")[0];
        messages.add(Pair.of(threadNameWithoutNumber, String.valueOf(message)));
        System.out.println((System.currentTimeMillis() - start) + "\t|" +
                threadName + "\t|" +
                            message
        );
    }

    public static void clear() {
        messages.clear();
    }
}
