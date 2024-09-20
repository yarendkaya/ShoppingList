package com.yarendemirkaya.shoppinglist.ui.shoppinglist

import com.yarendemirkaya.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)

}