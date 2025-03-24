package com.ascoding.transfluent.app

import android.app.Application
import com.ascoding.transfluent.di.initKoin
import org.koin.android.ext.koin.androidContext

class TransFluentApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@TransFluentApp)
        }
    }
}