package com.bank.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("balances")
    private Set<BalanceDto> balanceDtoSet;
}
