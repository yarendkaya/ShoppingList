package com.yarendemirkaya.shoppinglist

import android.app.Application
import com.yarendemirkaya.shoppinglist.di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class ShoppingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ShoppingApplication)
            modules(appModule)
        }
    }

}