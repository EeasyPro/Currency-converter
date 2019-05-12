package com.example.modulbankhw5

import rx.Observable


interface CurrencyDataProvider {
    fun getCurrencies(base: String?):
            Observable<CurrencyResponse>
}