package com.financial.cardtransactionprocessor.card.model

import com.financial.cardtransactionprocessor.card.entity.TransactionEntity
import java.time.LocalDateTime

data class Transaction(
    val id: Long? = null,
    val cardToken: String,
    val merchantId: String,
    val amount: Double,
    val currency: String,
    val isInstallment: Boolean = false,
    val installments: Int,
    val firstInstallmentAmount: Double? = null,
    val otherInstallmentAmount: Double? = null,
    val status: String,
    val reason: String,
    val createdAt: LocalDateTime? = null,
){

    constructor(transactionEntity: TransactionEntity) : this(
        id = transactionEntity.id,
        cardToken = transactionEntity.cardToken,
        merchantId = transactionEntity.merchantId,
        amount = transactionEntity.amount,
        currency = transactionEntity.currency,
        isInstallment = transactionEntity.isInstallment,
        installments = transactionEntity.installments,
        firstInstallmentAmount = transactionEntity.firstInstallmentAmount,
        otherInstallmentAmount = transactionEntity.otherInstallmentAmount,
        status = transactionEntity.status,
        reason = transactionEntity.reason,
        createdAt = transactionEntity.createdAt
    )

    fun toEntity(): TransactionEntity {
        return TransactionEntity(
            id = id!!,
            cardToken = cardToken,
            merchantId = merchantId,
            amount = amount,
            currency = currency,
            isInstallment = isInstallment,
            installments = installments,
            firstInstallmentAmount = firstInstallmentAmount,
            otherInstallmentAmount = otherInstallmentAmount,
            status = status,
            reason = reason,
        )
    }
}