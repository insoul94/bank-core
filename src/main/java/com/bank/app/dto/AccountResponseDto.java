package com.bank.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    @NotNull
    @Positive
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Positive
    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("balances")
    private Set<BalanceDto> balanceDtoSet;
}
