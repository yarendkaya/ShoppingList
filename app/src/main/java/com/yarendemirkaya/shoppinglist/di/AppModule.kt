package com.yarendemirkaya.shoppinglist.di

import com.yarendemirkaya.shoppinglist.data.db.ShoppingDatabase
import com.yarendemirkaya.shoppinglist.data.repositories.ShoppingRepository
import com.yarendemirkaya.shoppinglist.ui.shoppinglist.ShoppingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val appModule = module {
        viewModel { ShoppingViewModel() }
        single<ShoppingDatabase> { ShoppingDatabase.invoke(androidContext()) }
        single<ShoppingRepository> { ShoppingRepository(get()) }
    }
}