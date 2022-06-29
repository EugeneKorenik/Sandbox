package com.telegram.messaging.repository;

import com.telegram.messaging.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("""
            select a from Account a
            join DialogueWatcher dw
            on a.id = dw.watcher.id
            where dw.target.id = :dialogueId
            and dw.deleted = false
            """)
    List<Account> findAllWatchersByDialogueId(@Param("dialogueId") UUID dialogueId);

}
