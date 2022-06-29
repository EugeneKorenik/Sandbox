package com.telegram.messaging.entity.watcher;

import com.telegram.messaging.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseWatcherId<T extends BaseEntity, S extends BaseEntity> {

    @Column(name = "target_id", columnDefinition = "uuid not null", updatable = false)
    protected UUID targetId;

    @Column(name = "watcher_id", columnDefinition = "uuid not null", updatable = false)
    protected UUID watcherId;

    public BaseWatcherId(T target, S watcher) {
        this.targetId = target.getId();
        this.watcherId = watcher.getId();
    }

}
