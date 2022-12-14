package com.bank.app.mapper;

import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.entity.Transaction;

public class TransactionMapper {

    /**
     * @param entity Transaction
     * @return TransactionResponseDto with null 'balance' field.
     */
    public static TransactionResponseDto toResponseDto(Transaction entity) {
        if (entity == null) {
            return null;
        }
        return TransactionResponseDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccount().getId())
                .amount(entity.getAmount().toString())
                .currency(entity.getCurrency())
                .direction(entity.getDirection())
                .description(entity.getDescription())
                .build();
    }
}
