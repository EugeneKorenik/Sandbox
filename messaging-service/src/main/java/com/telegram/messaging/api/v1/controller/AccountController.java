package com.telegram.messaging.api.v1.controller;

import com.telegram.messaging.api.v1.dto.AccountDto;
import com.telegram.messaging.api.v1.mapper.AccountMapper;
import com.telegram.messaging.entity.Account;
import com.telegram.messaging.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/v1/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService,
                             AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAll() {
        List<Account> accounts = accountService.findAll();
        List<AccountDto> accountDtoList = accounts.stream()
                .map(accountMapper::toDto)
                .toList();

        return ResponseEntity.ok(accountDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("id") UUID accountId) {
        Account account = accountService.findById(accountId);
        AccountDto accountDto = accountMapper.toDto(account);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account = accountService.create(account);
        accountDto = accountMapper.toDto(account);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountDto);
    }

    @PutMapping
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto accountDto) {
        Account entity = accountMapper.toEntity(accountDto);
        entity = accountService.update(entity);
        accountDto = accountMapper.toDto(entity);
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

}
