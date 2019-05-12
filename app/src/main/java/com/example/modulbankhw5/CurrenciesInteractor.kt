package com.example.modulbankhw5

import rx.Observable

interface CurrenciesInteractor {
    fun getCurrencies(base: String?):
            Observable<CurrencyResponse>
}
