package com.tuum.app.mapper;

import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.entity.Transaction;

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
                .accountId(entity.getAccountId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .direction(entity.getDirection())
                .description(entity.getDescription())
                .build();
    }
}
