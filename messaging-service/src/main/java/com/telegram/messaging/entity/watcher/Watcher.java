package com.telegram.messaging.entity.watcher;

import com.telegram.messaging.entity.BaseEntity;

public interface Watcher<T extends BaseEntity, S extends BaseEntity> {

    BaseWatcherId<T, S> getId();

    T getTarget();

    S getWatcher();

}
