package com.telegram.messaging.repository;

import com.telegram.messaging.entity.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DialogueRepository extends JpaRepository<Dialogue, UUID> {

    @Query("""
            select d from Dialogue d
            join DialogueWatcher dw
            on d.id = dw.target.id
            where dw.watcher.id = :accountId
            and dw.deleted = false
            """)
    List<Dialogue> findAllByAccountId(@Param("accountId") UUID accountId);

}
