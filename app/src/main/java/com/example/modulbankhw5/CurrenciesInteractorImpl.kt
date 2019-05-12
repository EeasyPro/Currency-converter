package com.example.modulbankhw5

import rx.Observable

class CurrenciesInteractorImpl(private val repository: CurrencyDataProvider) : CurrenciesInteractor {
    override fun getCurrencies(base: String?):
            Observable<CurrencyResponse> {
        return repository.getCurrencies(base)
    }
}