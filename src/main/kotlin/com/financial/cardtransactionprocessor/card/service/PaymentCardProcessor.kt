package com.financial.cardtransactionprocessor.card.service

import com.financial.cardtransactionprocessor.card.dto.NewTransactionProcess
import com.financial.cardtransactionprocessor.card.entity.StatusTransaction
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
abstract class PaymentCardProcessor {

    suspend fun processPayment(transaction: NewTransactionProcess): StatusTransaction = coroutineScope {
        val cardTokenValidation = async { validateCardToken(transaction.cardToken) }
        val balanceCardValidation = async { checkBalanceCard(transaction.cardToken, transaction.amountTransaction) }
        val antifraudValidation = async { validateAntifraud(transaction) }

        val cardToken = cardTokenValidation.await()
        val balanceCard = balanceCardValidation.await()
        val antifraud = antifraudValidation.await()


        if (cardToken && balanceCard && antifraud) {
            processTransaction(transaction)
        } else {
            when {
                !cardToken -> StatusTransaction.DENIED_BY_CARD
                !balanceCard -> StatusTransaction.DENIED_BY_BALANCE
                else -> StatusTransaction.DENIED_BY_FRAUD
            }
        }
    }


    protected abstract suspend fun validateCardToken(token: String): Boolean
    protected abstract suspend fun checkBalanceCard(token: String, amount: Int): Boolean
    protected abstract suspend fun validateAntifraud(transaction: NewTransactionProcess): Boolean
    protected abstract suspend fun processTransaction(transaction: NewTransactionProcess): StatusTransaction
}