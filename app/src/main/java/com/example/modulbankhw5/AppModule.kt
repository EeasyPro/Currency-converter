package com.example.modulbankhw5

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module

class AppModule {

    @Provides
    fun provideApi() : CurrenciesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    fun provideDataProvider(api : CurrenciesApi) : CurrencyDataProvider {
        return CurrencyDataProviderImpl(api)
    }

    @Provides
    fun provideInteractor(repository : CurrencyDataProvider) : CurrenciesInteractor {
        return CurrenciesInteractorImpl(repository)
    }

}