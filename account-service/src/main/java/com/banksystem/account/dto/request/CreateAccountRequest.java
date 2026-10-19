
package com.banksystem.account.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequest(
    @NotNull(message = "customerId không được để trống")
    @Positive(message = "customerId phải lớn hơn 0")
    Long customerId,

    @NotNull(message = "accountType không được để trống")
    @Pattern(
        regexp = "SAVINGS|PAYMENT",
        message = "accountType phải là SAVINGS hoặc PAYMENT"
    )
    String accountType
) {}