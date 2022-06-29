package com.telegram.messaging.repository;

import com.telegram.messaging.entity.Dialogue;
import com.telegram.messaging.entity.watcher.DialogueWatcher;
import com.telegram.messaging.entity.watcher.DialogueWatcherId;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogueWatcherRepository extends BaseAccountWatcherRepository<Dialogue, DialogueWatcher, DialogueWatcherId> {

}
