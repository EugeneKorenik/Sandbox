package com.telegram.messaging.service.watcher;

import java.util.List;
import java.util.UUID;

public interface WatcherService {

    void addWatcher(UUID target, UUID watcherId);

    void addAllWatchers(UUID target, List<UUID> watcherIdList);

    void removeWatcher(UUID target, UUID watcherId);

    void removeAllWatchers(UUID target, List<UUID> watcherIdList);

    void removeAllWatchers(UUID target);

}

