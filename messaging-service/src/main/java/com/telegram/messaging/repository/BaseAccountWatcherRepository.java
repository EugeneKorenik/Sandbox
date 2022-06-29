package com.telegram.messaging.repository;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.BaseEntity;
import com.telegram.messaging.entity.watcher.BaseWatcherId;
import com.telegram.messaging.entity.watcher.Watcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseAccountWatcherRepository<T extends BaseEntity,
        W extends Watcher<T, Account>,
        I extends BaseWatcherId<T, Account>> extends JpaRepository<W, I> {

    @Modifying
    @Query("""
            update #{#entityName} w set w.deleted = true
            where w.id.targetId = :targetId
            """)
    void markDeletedForAll(UUID targetId);

    @Modifying
    @Query("""
            update #{#entityName} w set w.deleted = true
            where w.id.targetId = :targetId
            and w.id.watcherId in :watcherIdList
            """)
    void markDeletedForAllIn(UUID targetId, List<UUID> watcherIdList);

    @Modifying
    @Query("""
            update #{#entityName} w set w.deleted = true
            where w.id.targetId = :targetId
            and w.id.watcherId = :watcherId
            """)
    void markDeletedByWatcher(UUID targetId, UUID watcherId);

}
