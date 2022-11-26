package com.bank.app.mapper;

import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;
import com.bank.app.exception.SystemException;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionMapper {

    public static Transaction toEntity(@NotNull TransactionRequestDto requestDto, @NotNull Account account) {
        if (requestDto == null) {
            throw new SystemException("requestDto must not be null");
        }
        if (account == null) {
            throw new SystemException("account must not be null");
        }
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
        if (entity == null) {
            throw new SystemException("entity must not be null");
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

    public static TransactionResponseDto toResponseDto(@NotNull Transaction entity, @NotNull BigDecimal balanceAfter) {
        if (balanceAfter == null) {
            throw new SystemException("balanceAfter must not be null");
        }
        TransactionResponseDto responseDto = toResponseDto(entity);
        responseDto.setBalanceAfter(balanceAfter.toString());
        return responseDto;
    }

    public static List<TransactionResponseDto> toResponseDtoList(List<Transaction> entityList) {
        if (entityList == null || entityList.size() == 0) {
            return new ArrayList<>();
        }
        return entityList.stream().map(TransactionMapper::toResponseDto).toList();
    }
}
