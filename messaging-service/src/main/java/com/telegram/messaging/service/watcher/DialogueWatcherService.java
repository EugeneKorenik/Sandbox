package com.telegram.messaging.service.watcher;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Dialogue;
import com.telegram.messaging.entity.watcher.DialogueWatcher;
import com.telegram.messaging.repository.DialogueWatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DialogueWatcherService {

    private final DialogueWatcherRepository watcherRepository;
    private final MessageWatcherService messageWatcherService;

    @Autowired
    public DialogueWatcherService(DialogueWatcherRepository watcherRepository,
                                  MessageWatcherService messageWatcherService) {
        this.watcherRepository = watcherRepository;
        this.messageWatcherService = messageWatcherService;
    }

    public void addWatchers(Dialogue dialogue, List<Account> accountWatcherList) {
        List<DialogueWatcher> watcherList = accountWatcherList.stream()
                .map(account -> new DialogueWatcher(dialogue, account))
                .collect(Collectors.toList());

        watcherRepository.saveAll(watcherList);
    }

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
