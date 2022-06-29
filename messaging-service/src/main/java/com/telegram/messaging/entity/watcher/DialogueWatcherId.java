package com.telegram.messaging.entity.watcher;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Dialogue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Embeddable
@NoArgsConstructor
public class DialogueWatcherId extends BaseWatcherId<Dialogue, Account> implements Serializable {

    public DialogueWatcherId(Dialogue dialogue, Account accountWatcher) {
        super(dialogue, accountWatcher);
    }

}
