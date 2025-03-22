package com.financial.cardtransactionprocessor.card.dto

import com.financial.cardtransactionprocessor.card.entity.Brand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class NewTransactionProcess(
    @NotNull
    @NotBlank
    val cardToken: String,

    @NotNull
    @NotBlank
    val brand: Brand,

    @NotNull
    @NotBlank
    val merchantId: String,

    @NotNull
    val amountTransaction: Int,

    @NotNull
    @NotBlank
    val currency: String = "BRL",

    @NotNull
    val isInstallment: Boolean = false,

    @NotNull
    val installments: Int,

    @NotNull
    val firstInstallmentAmount: Int,

    @NotBlank
    val otherInstallmentAmount: Int? = null
)
