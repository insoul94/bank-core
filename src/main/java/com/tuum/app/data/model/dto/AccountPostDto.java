package com.tuum.app.data.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.app.data.model.entity.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.*;

@Data
public class AccountPostDto {
    @NotNull
    @JsonProperty("customerId")
    private Long customerId;

    @JsonProperty("country")
    private String country;

    @JsonProperty("currencies")
    private Set<Currency> currencies;
}
