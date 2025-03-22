package com.financial.cardtransactionprocessor.card.service

import com.financial.cardtransactionprocessor.card.dto.NewTransactionProcess
import com.financial.cardtransactionprocessor.card.entity.Brand
import com.financial.cardtransactionprocessor.card.entity.TransactionEntity
import com.financial.cardtransactionprocessor.card.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class TransactionProcessorService(
    private val transactionRepository: TransactionRepository
) {

    suspend fun processTransaction(transaction: NewTransactionProcess): TransactionEntity {
        val statusProcessor =  getBrandProcessor(transaction.brand).processPayment(transaction)
        val transactionEntity = TransactionEntity(
            transaction,
            status = statusProcessor
        )
        return withContext(Dispatchers.IO) {
            transactionRepository.save(transactionEntity)
        }
    }

    suspend fun getBrandProcessor(brand: Brand): PaymentCardProcessor {
        return when (brand) {
            Brand.VISA -> VisaCardProcessor()
            Brand.MASTERCARD -> MasterCardProcessor()
            Brand.ELO -> EloCardProcessor()
            Brand.HIPERCARD -> HipercardCardProcessor()
            Brand.AMEX -> AmexCardProcessor()
        }
    }
}