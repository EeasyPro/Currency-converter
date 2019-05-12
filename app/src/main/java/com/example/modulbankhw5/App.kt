package com.example.modulbankhw5

import android.app.Application

class App:Application(){
    companion object{
        lateinit var appComponent:AppComp
    }
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComp
            .builder()
            .appModule(AppModule())
            .build()
    }
}