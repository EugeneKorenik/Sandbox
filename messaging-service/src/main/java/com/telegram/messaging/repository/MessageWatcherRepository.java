package com.telegram.messaging.repository;

import com.telegram.messaging.entity.Message;
import com.telegram.messaging.entity.watcher.MessageWatcher;
import com.telegram.messaging.entity.watcher.MessageWatcherId;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageWatcherRepository extends BaseAccountWatcherRepository<Message, MessageWatcher, MessageWatcherId> {
/*
    @Query("""
            update MessageWatcher set deleted = true
            where id.targetId = :targetId
            """)
    void markDeletedForAll(UUID targetId);

    @Query("""
            update MessageWatcher set deleted = true
            where id.targetId = :targetId
            and id.watcherId = :watcherId
            """)
    void makeDeletedForWatcher(UUID targetId, UUID watcherId);
*/
}
