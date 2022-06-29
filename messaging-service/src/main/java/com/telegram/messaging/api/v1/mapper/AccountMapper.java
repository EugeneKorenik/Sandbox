package com.telegram.messaging.api.v1.mapper;

import com.telegram.messaging.api.v1.dto.AccountDto;
import com.telegram.messaging.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface AccountMapper {

    AccountDto toDto(Account account);

    Account toEntity(AccountDto account);

}
