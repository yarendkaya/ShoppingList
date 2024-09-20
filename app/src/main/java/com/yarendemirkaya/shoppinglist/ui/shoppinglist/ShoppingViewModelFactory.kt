package com.yarendemirkaya.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yarendemirkaya.shoppinglist.data.repositories.ShoppingRepository

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
) : ViewModelProvider.NewInstanceFactory() {

    fun <T : ViewModel?> create(modelClass: Class<T>): T { //burada override vardı?
        return ShoppingViewModel(repository) as T
    }
}