package com.yarendemirkaya.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yarendemirkaya.shoppinglist.data.db.entities.ShoppingItem

// we have to specify which entity belong to our database.
@Database(
    entities = [ShoppingItem::class], // there could be many entities because of that we need to say which array is.
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao //with this function we can access our database operations
    // from inside the database class.


    companion object {
        @Volatile //if we dont say this keyword for example there are two threads and both want you
        // initialize it at the same time. and it is a problem.
        private var instance: ShoppingDatabase? = null
        private var LOCK= Any()


        //whenever we create an instance of shoppingDatabase class this function will be executed.
        operator fun invoke(context: Context)= instance ?: synchronized(LOCK) { // synchronized ifadesi  instance null ise diğer threadlerin erişmesini engeller.
            instance ?: createDatabase(context).also {
                instance =it
            }
        }
        private fun createDatabase(context: Context) =//context nedir
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db"
            ).build()
    }
}
