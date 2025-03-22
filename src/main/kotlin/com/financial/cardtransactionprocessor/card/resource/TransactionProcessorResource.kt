package com.financial.cardtransactionprocessor.card.resource

import com.financial.cardtransactionprocessor.card.dto.NewTransactionProcess
import com.financial.cardtransactionprocessor.card.service.TransactionProcessorService
import jakarta.validation.Valid
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/card/processor")
class TransactionProcessorResource(
    private val transactionProcessorService: TransactionProcessorService
) {

    @OptIn(DelicateCoroutinesApi::class)
    @PostMapping()
    fun processTransaction(@Valid @RequestBody newTransactionProcess: NewTransactionProcess): CompletableFuture<Void> {
        return GlobalScope.async {
            transactionProcessorService.processTransaction(newTransactionProcess)
        }.asCompletableFuture().thenApply { null }
    }
}