package com.yarendemirkaya.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.yarendemirkaya.shoppinglist.R
import com.yarendemirkaya.shoppinglist.data.db.entities.ShoppingItem
import com.yarendemirkaya.shoppinglist.ui.shoppinglist.ShoppingViewModel


class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel//adaptera viewModel gönderilmez. Bunu araştır.We need
// to delete the data from our adapter because we have to delete imageview so we need to call the delete
// function from the viewmodel inside of adapter class.
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {


        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        val builder: AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context) //?
        builder
            .setMessage("Do you really want to delete this item")
            .setTitle("Delete Item")
            .setPositiveButton("YES") { dialog, which ->
                    viewModel.delete(curShoppingItem)

            }
            .setNegativeButton("NO") { dialog, which ->
                dialog.dismiss()
            }

        val deleteItemDialog: AlertDialog = builder.create()


        holder.itemView.findViewById<TextView>(R.id.tvName).text = curShoppingItem.name
        holder.itemView.findViewById<TextView>(R.id.tvAmount).text = "${curShoppingItem.amount}"


        // neden setonclicklistenerlar burada?
        holder.itemView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivPlus).setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivMinus).setOnClickListener {
            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                if (curShoppingItem.amount == 0) {
                    deleteItemDialog.show()
                } else {
                    viewModel.upsert(curShoppingItem)
                }
            }
        }
    }
}