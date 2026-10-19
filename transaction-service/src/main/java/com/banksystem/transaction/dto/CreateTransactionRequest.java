package com.banksystem.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateTransactionRequest(

    @NotNull(message = "sourceAccountId không được để trống")
    @Positive(message = "sourceAccountId phải lớn hơn 0")
    Long sourceAccountId,

    @NotNull(message = "destinationAccountId không được để trống")
    @Positive(message = "destinationAccountId phải lớn hơn 0")
    Long destinationAccountId,

    @NotNull(message = "amount không được để trống")
    @DecimalMin(value = "0.01",
            message = "amount phải lớn hơn 0")
    @Digits(integer = 17, fraction = 2,
            message = "amount tối đa 2 chữ số thập phân")
    BigDecimal amount,

    @Size(max = 255, message = "description tối đa 255 ký tự")
    String description
) {}