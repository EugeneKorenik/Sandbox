package com.telegram.messaging.entity.watcher;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Embeddable
@NoArgsConstructor
public class MessageWatcherId extends BaseWatcherId<Message, Account> implements Serializable {

    public MessageWatcherId(Message message, Account accountWatcher) {
        super(message, accountWatcher);
    }

}
