package com.yarendemirkaya.shoppinglist.ui.shoppinglist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yarendemirkaya.shoppinglist.R
import com.yarendemirkaya.shoppinglist.adapter.ShoppingItemAdapter
import com.yarendemirkaya.shoppinglist.data.db.ShoppingDatabase
import com.yarendemirkaya.shoppinglist.data.db.entities.ShoppingItem
import com.yarendemirkaya.shoppinglist.data.repositories.ShoppingRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

         //val database = ShoppingDatabase(this) //bunlar bad practice. DI olayına tamamen ters. tightly coupled yapmıs olursun.
         //val repository = ShoppingRepository(database)
        // val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this)[ShoppingViewModel::class.java]


        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        val rvShoppingItems = findViewById<RecyclerView>(R.id.rvShoppingItems)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }

    }
}