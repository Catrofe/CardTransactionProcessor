package com.financial.cardtransactionprocessor.card.entity

enum class StatusTransaction {
    APPROVED,
    DENIED_BY_BALANCE,
    DENIED_BY_FRAUD,
    DENIED_BY_SYSTEM,
    DENIED_BY_CARD
}