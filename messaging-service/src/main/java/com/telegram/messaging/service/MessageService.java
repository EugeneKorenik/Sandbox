package com.telegram.messaging.service;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Dialogue;
import com.telegram.messaging.entity.Message;
import com.telegram.messaging.repository.MessageRepository;
import com.telegram.messaging.service.watcher.MessageWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final AccountService accountService;
    private final DialogueService dialogueService;
    private final MessageRepository messageRepository;
    private final MessageWatcherService messageWatcherService;
    private final TransactionService transactionService;

    @Autowired
    public MessageService(AccountService accountService,
                          DialogueService dialogueService,
                          MessageRepository messageRepository,
                          MessageWatcherService messageWatcherService,
                          TransactionService transactionService) {
        this.accountService = accountService;
        this.dialogueService = dialogueService;
        this.messageRepository = messageRepository;
        this.messageWatcherService = messageWatcherService;
        this.transactionService = transactionService;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findAllByDialogueIdAndAccountId(UUID dialogueId, UUID accountId) {
        return messageRepository.findAllByDialogueIdAndAccountId(dialogueId, accountId);
    }

    @Transactional
    public Message sendMessage(String text, UUID senderAccountId, UUID dialogueId, UUID parentMessageId) {
        Message message = transactionService.runInRequiresNew(() -> saveMessage(text, senderAccountId, dialogueId, parentMessageId));
        addMessageWatchers(message, dialogueId);
        return message;
    }

    private Message saveMessage(String text, UUID senderAccountId, UUID dialogueId, UUID parentMessageId) {
        Account sender = accountService.findById(senderAccountId);
        Dialogue dialogue = dialogueService.findById(dialogueId);
        Message parentMessage = messageRepository.findById(parentMessageId).orElse(null);
        Message newMessage = Message.builder()
                .text(text)
                .sender(sender)
                .dialogue(dialogue)
                .parentMessage(parentMessage)
                .build();

        return messageRepository.save(newMessage);
    }

    private void addMessageWatchers(Message savedMessage, UUID dialogueId) {
        List<Account> accounts = accountService.findAllWatcherByDialogueId(dialogueId);
        messageWatcherService.addWatchers(savedMessage, accounts);
    }

    public Message update(Message updatedMessage) {
        UUID messageId = updatedMessage.getId();
        Message message = messageRepository.getById(messageId);
        message.setText(updatedMessage.getText());
        return messageRepository.save(message);
    }

    public void deleteForWatcher(UUID messageId, UUID accountId) {
        messageWatcherService.removeWatcher(messageId, accountId);
    }

    public void deleteForAll(UUID messageId) {
        messageWatcherService.removeAllWatchers(messageId);
    }

}
