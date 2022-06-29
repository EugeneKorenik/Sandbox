package com.telegram.messaging.repository;

import com.telegram.messaging.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    @Query("""
            select m from Message m
            join MessageWatcher  mw
            on mw.id.targetId = m.id
            where m.dialogue.id = :dialogueId
            and mw.watcher.id = :accountId
            and mw.deleted = false
            """)
    List<Message> findAllByDialogueIdAndAccountId(@Param("dialogueId") UUID dialogueId,
                                                  @Param("accountId") UUID accountId);

}
