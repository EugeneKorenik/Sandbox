package com.telegram.messaging.entity.watcher;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Dialogue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "DialogueWatcher")
@Table(name = "dialogue_watcher")
public class DialogueWatcher implements Watcher<Dialogue, Account> {

    @EmbeddedId
    private DialogueWatcherId id;

    @MapsId("targetId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dialogue target;

    @MapsId("watcherId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account watcher;

    @Column(name = "deleted", columnDefinition = "bool not null default false")
    private boolean deleted;

    public DialogueWatcher(Dialogue target, Account watcher) {
        this.id = new DialogueWatcherId(target, watcher);
        this.target = target;
        this.watcher = watcher;
    }

}
