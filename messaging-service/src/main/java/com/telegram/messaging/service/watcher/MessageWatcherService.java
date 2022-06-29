package com.telegram.messaging.service.watcher;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Message;
import com.telegram.messaging.entity.watcher.MessageWatcher;
import com.telegram.messaging.repository.MessageWatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageWatcherService {

    private final MessageWatcherRepository watcherRepository;

    @Autowired
    public MessageWatcherService(MessageWatcherRepository watcherRepository) {
        this.watcherRepository = watcherRepository;
    }

    public List<MessageWatcher> findAll() {
        return watcherRepository.findAll();
    }

    public void addWatchers(Message message, List<Account> accountWatchers) {
        List<MessageWatcher> watcherList = accountWatchers.stream()
                .map(account -> new MessageWatcher(message, account))
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
