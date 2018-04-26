package common;

import rx.Observable;

public interface TcpResource {
    Observable<String> performRequest(String URI);
}
