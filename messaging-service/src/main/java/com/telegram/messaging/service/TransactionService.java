package com.telegram.messaging.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@Service
public class TransactionService {

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public <T> T runInRequiresNew(Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Object runInRequires(Supplier<?> supplier) {
        return supplier.get();
    }

}
