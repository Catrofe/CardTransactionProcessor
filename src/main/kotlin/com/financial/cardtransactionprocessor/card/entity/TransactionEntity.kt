package com.financial.cardtransactionprocessor.card.entity

import com.financial.cardtransactionprocessor.card.dto.NewTransactionProcess
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(
    indexes = [
        Index(name = "idx_merchant_id", columnList = "merchantId"),
        Index(name = "idx_created_at", columnList = "createdAt"),
        Index(name = "idx_status", columnList = "status"),
        Index(name = "idx_brand", columnList = "brand"),
        Index(name = "idx_external_id", columnList = "externalId"),
    ]
)
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val externalId: UUID,

    @Column(nullable = false, length = 36)
    val cardToken: String,

    @Column(nullable = false)
    val merchantId: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val brand: Brand,

    @Column(nullable = false)
    val amount: Int,

    @Column(nullable = false)
    val currency: String,

    @Column(nullable = false)
    val isInstallment: Boolean = false,

    @Column(nullable = false)
    val installments : Int,

    @Column(nullable = true)
    val firstInstallmentAmount: Int,

    @Column(nullable = true)
    val otherInstallmentAmount: Int?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StatusTransaction,

    @Column(nullable = false)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    )
{
    constructor(transaction: NewTransactionProcess, status: StatusTransaction) : this(
        id = 0,
        externalId = UUID.randomUUID(),
        cardToken = transaction.cardToken,
        merchantId = transaction.merchantId,
        brand = transaction.brand,
        amount = transaction.amountTransaction,
        currency = transaction.currency,
        isInstallment = transaction.isInstallment,
        installments = transaction.installments,
        firstInstallmentAmount = transaction.firstInstallmentAmount,
        otherInstallmentAmount = transaction.otherInstallmentAmount,
        status = status,
        createdAt = LocalDateTime.now()
    )
}
