package com.example.modulbankhw5

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface CurrenciesApi {

    @GET("/latest")
    fun getCurrencies(@Query("base")base:String?):
            Observable<CurrencyResponse>
}