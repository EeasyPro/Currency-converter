package com.example.modulbankhw5

import com.arellomobile.mvp.MvpView

interface CurrenciesView :MvpView{
fun showProgress()
    fun showError(e:Throwable)
    fun showCurrencies(viewData: CurrencyResponse)
    fun clearData()
}
