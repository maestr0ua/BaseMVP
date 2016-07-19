package com.example.klim.basemvp.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private static volatile RxBus mDefaultInstance;

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    private RxBus() {
    }

    public static RxBus getInstance() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    public void send(Object o) {
        _bus.onNext(o);
    }

    public <T extends Object> Observable<T> connect(final Class<T> eventType) {
        return _bus.filter(o -> {
            Logger.d("new event, class = " + eventType.getName());
            return o.getClass().equals(eventType);
        }).cast(eventType);
    }

}
