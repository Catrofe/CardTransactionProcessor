package com.financial.cardtransactionprocessor.card.service

import com.financial.cardtransactionprocessor.card.dto.NewTransactionProcess
import com.financial.cardtransactionprocessor.card.entity.StatusTransaction
import kotlinx.coroutines.delay
import org.springframework.stereotype.Component
import kotlin.random.Random

@Suppress("MagicNumber")
@Component
class HipercardCardProcessor : PaymentCardProcessor() {

    override suspend fun validateCardToken(token: String): Boolean {
        delay(Random.nextLong(15, 150))
        return Random.nextDouble() < 0.85 // 85% de sucesso
    }

    override suspend fun checkBalanceCard(token: String, amount: Int): Boolean {
        delay(Random.nextLong(10, 180))
        return Random.nextDouble() < 0.63 // 63% de sucesso
    }

    override suspend fun validateAntifraud(transaction: NewTransactionProcess): Boolean {
        delay(Random.nextLong(10, 250))
        return Random.nextDouble() < 0.69 // 69% de sucesso
    }

    override suspend fun processTransaction(transaction: NewTransactionProcess): StatusTransaction {
        delay(Random.nextLong(10, 250))
        val random = Random.nextDouble() < 0.65
        return if (random) {
            StatusTransaction.APPROVED
        } else {
            StatusTransaction.DENIED_BY_SYSTEM
        }
    }
}