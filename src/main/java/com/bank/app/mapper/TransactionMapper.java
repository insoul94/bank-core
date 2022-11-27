package com.bank.app.mapper;

import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionMapper {

    public static Transaction toEntity(@NotNull TransactionRequestDto requestDto, @NotNull Account account) {
        return Transaction.builder()
                .account(account)
                .amount(new BigDecimal(requestDto.getAmount()))
                .currency(requestDto.getCurrency())
                .direction(requestDto.getDirection())
                .description(requestDto.getDescription())
                .build();
    }

    /**
     * @param entity Transaction
     * @return TransactionResponseDto with null 'balanceAfter' field.
     */
    public static TransactionResponseDto toResponseDto(@NotNull Transaction entity) {
        return TransactionResponseDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccount().getId())
                .amount(entity.getAmount().toString())
                .currency(entity.getCurrency())
                .direction(entity.getDirection())
                .description(entity.getDescription())
                .build();
    }

    public static TransactionResponseDto toResponseDto(@NotNull Transaction entity, @NotNull BigDecimal balanceAfter) {
        TransactionResponseDto responseDto = toResponseDto(entity);
        responseDto.setBalanceAfter(balanceAfter.toString());
        return responseDto;
    }

    public static List<TransactionResponseDto> toResponseDtoList(@NotNull List<Transaction> entityList) {
        if (entityList.size() == 0) {
            return new ArrayList<>();
        }
        return entityList.stream().map(TransactionMapper::toResponseDto).toList();
    }
}
