package com.telegram.messaging.service;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Dialogue;
import com.telegram.messaging.repository.DialogueRepository;
import com.telegram.messaging.service.watcher.DialogueWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DialogueService {

    private final AccountService accountService;
    private final DialogueRepository dialogueRepository;
    private final DialogueWatcherService dialogueWatcherService;
    private final TransactionService transactionService;

    @Autowired
    public DialogueService(AccountService accountService,
                           DialogueRepository dialogueRepository,
                           DialogueWatcherService dialogueWatcherService,
                           TransactionService transactionService) {
        this.accountService = accountService;
        this.dialogueRepository = dialogueRepository;
        this.dialogueWatcherService = dialogueWatcherService;
        this.transactionService = transactionService;
    }

    public Dialogue findById(UUID dialogueId) {
        return dialogueRepository.getById(dialogueId);
    }

    public List<Dialogue> findAllByAccountId(UUID accountId) {
        return dialogueRepository.findAllByAccountId(accountId);
    }

    @Transactional
    public Dialogue create(String dialogueName, List<UUID> accountWatcherIdList) {
        Dialogue dialogue = transactionService.runInRequiresNew(() -> save(dialogueName));
        addDialogueWatchers(dialogue, accountWatcherIdList);
        return dialogue;
    }

    public Dialogue save(String dialogueName) {
        Dialogue dialogue = new Dialogue();
        dialogue.setName(dialogueName);
        return dialogueRepository.save(dialogue);
    }

    public void addDialogueWatchers(Dialogue dialogue, List<UUID> accountWatcherIdList) {
        List<Account> accountWatcherList = accountService.findAllById(accountWatcherIdList);
        dialogueWatcherService.addWatchers(dialogue, accountWatcherList);
    }

    public void deleteForWatcher(UUID dialogueId, UUID accountId) {
        dialogueWatcherService.removeWatcher(dialogueId, accountId);
    }

    public void deleteForAll(UUID dialogueId) {
        dialogueWatcherService.removeAllWatchers(dialogueId);
    }

}
