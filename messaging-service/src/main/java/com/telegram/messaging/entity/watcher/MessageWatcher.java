package com.telegram.messaging.entity.watcher;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "MessageWatcher")
@Table(name = "message_watcher")
public class MessageWatcher implements Watcher<Message, Account> {

    @EmbeddedId
    private MessageWatcherId id;

    @MapsId("targetId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Message target;

    @MapsId("watcherId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account watcher;

    @Column(name = "deleted", columnDefinition = "bool not null default false")
    private boolean deleted;

    public MessageWatcher(Message target, Account watcher) {
        this.id = new MessageWatcherId(target, watcher);
        this.target = target;
        this.watcher = watcher;
    }
}
