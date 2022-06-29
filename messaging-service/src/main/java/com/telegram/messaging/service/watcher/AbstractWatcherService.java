package com.telegram.messaging.service.watcher;

import com.telegram.messaging.repository.BaseAccountWatcherRepository;
import com.telegram.messaging.service.AccountService;

import java.util.List;
import java.util.UUID;

abstract class AbstractWatcherService {

    protected AccountService accountService;
    protected BaseAccountWatcherRepository<?, ?, ?> watcherRepository;

    AbstractWatcherService(AccountService accountService,
                           BaseAccountWatcherRepository<?, ?, ?> watcherRepository) {
        this.watcherRepository = watcherRepository;
        this.accountService = accountService;
    }

    public abstract void addWatcher(UUID dialogueId, UUID watcherId);

    public abstract void addAllWatchers(UUID targetId, List<UUID> watcherIdList);

    public void removeWatcher(UUID targetId, UUID watcherId) {
        watcherRepository.markDeletedByWatcher(targetId, watcherId);
    }

    public void removeAllWatchers(UUID targetId, List<UUID> watcherIdList) {
        watcherRepository.markDeletedForAllIn(targetId, watcherIdList);
    }

    public void removeAllWatchers(UUID targetId) {
        watcherRepository.markDeletedForAll(targetId);
    }

}
