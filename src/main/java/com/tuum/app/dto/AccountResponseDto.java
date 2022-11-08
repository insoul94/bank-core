package com.tuum.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
public class AccountResponseDto {

    @NotNull
    @JsonProperty("account_id")
    private Long accountId;

    @NotNull
    @JsonProperty("customer_id")
    private Long customerId;

    private Set<BalanceDto> balances;
}
