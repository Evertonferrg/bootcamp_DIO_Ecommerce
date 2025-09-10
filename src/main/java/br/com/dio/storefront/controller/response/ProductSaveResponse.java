package br.com.dio.storefront.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSaveResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("name")String name,
        @JsonProperty("active") Boolean active
    ) {
}
