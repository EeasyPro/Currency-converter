package com.example.modulbankhw5

import android.graphics.ColorSpace
import android.util.Log
import android.widget.Spinner
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.modulbankhw5.R.id.spinner
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

@InjectViewState

class CurrenciesPresenter(private val interactor: CurrenciesInteractor): MvpPresenter<CurrenciesView>() {
    private var currenciesSubscription: Subscription? = null



    fun loadCurrencies(base: String?) {
        viewState.showProgress()
        currenciesSubscription = interactor
            .getCurrencies(base)
            .map { CurrencyResponse -> CurrencyResponse}
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::loadCurrenciesSuccess, this::loadCurrenciesError)
    }

    private fun loadCurrenciesSuccess(viewModel: CurrencyResponse) {
        viewState.showCurrencies(viewModel)
    }

    private fun loadCurrenciesError(throwable: Throwable) {
        viewState.showError(throwable)
    }

    fun refresh(currency : String){
        loadCurrencies(currency)
    }
}