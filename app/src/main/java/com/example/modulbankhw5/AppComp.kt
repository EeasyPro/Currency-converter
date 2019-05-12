package com.example.modulbankhw5

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton

interface AppComp {
    fun inject(screen: CurrenciesScreen)
}