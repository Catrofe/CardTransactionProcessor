package com.financial.cardtransactionprocessor.card.entity

import com.financial.cardtransactionprocessor.card.model.FlagCard
import com.financial.cardtransactionprocessor.card.model.StatusTransaction
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
        Index(name = "idx_flag_card", columnList = "flagCard"),
        Index(name = "idx_external_id", columnList = "externalId"),
    ]
)
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val externalId: UUID,

    @Column(nullable = false, length = 20)
    val cardToken: String,

    @Column(nullable = false)
    val merchantId: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val flagCard: FlagCard,

    @Column(nullable = false)
    val amount: Double,

    @Column(nullable = false)
    val currency: String,

    @Column(nullable = false)
    val isInstallment: Boolean = false,

    @Column(nullable = false)
    val installments : Int,

    @Column(nullable = true)
    val firstInstallmentAmount: Double?,

    @Column(nullable = true)
    val otherInstallmentAmount: Double?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StatusTransaction,

    @Column(nullable = true)
    val reason: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime? = LocalDateTime.now(),

    )
