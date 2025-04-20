package com.jetbrains.greeting.exchange

import com.jetbrains.greeting.network.Exchange
import com.jetbrains.greeting.network.ExchangeRepository
import com.jetbrains.greeting.shared.flowUtils.CFlow
import com.jetbrains.greeting.shared.flowUtils.toCommonFlow
import com.jetbrains.greeting.shared.utils.collectUnwrapped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ExchangeViewModel(
    val exchangeRepository: ExchangeRepository,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _exchangeFlow = MutableSharedFlow<
            List<Exchange>?>()
    val exchangeFlow: CFlow<List<Exchange>?>
        get() = _exchangeFlow.toCommonFlow()

    init {
        getExchanges().also {
            println("SEEHERE called api ")
        }
    }
    fun getExchanges(){
        viewModelScope.launch {
            exchangeRepository.getStockExchangesList()
                .collectUnwrapped(
                    onLoading = {
                        println("SEEHERE on loading")
                    },
                    onSuccess = {
                        println("SEEHERE  on success ${it}")
                        _exchangeFlow.emit(it)
                    }
                )
        }
    }
}