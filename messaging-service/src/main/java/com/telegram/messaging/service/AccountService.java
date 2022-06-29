package com.telegram.messaging.service;

import com.telegram.messaging.entity.Account;
import com.telegram.messaging.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Account> findAllById(List<UUID> accountIdList) {
        return accountRepository.findAllById(accountIdList);
    }

    public List<Account> findAllWatcherByDialogueId(UUID dialogueId) {
        return accountRepository.findAllWatchersByDialogueId(dialogueId);
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Account account) {
        UUID accountId = account.getId();
        Account accountFromDb = findById(accountId);

        if (account.getFirstName() != null) {
            accountFromDb.setFirstName(accountFromDb.getFirstName());
        }

        if (account.getSecondName() != null) {
            accountFromDb.setSecondName(account.getSecondName());
        }

        return accountRepository.save(accountFromDb);
    }

    public Account updateInfo(Account account) {
        return accountRepository.save(account);
    }

    public Account updateWithRelations(Account account) {
        return accountRepository.save(account);
    }

    public void deleteById(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

}
