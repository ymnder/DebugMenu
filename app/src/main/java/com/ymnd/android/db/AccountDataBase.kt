package com.ymnd.android.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ymnd.android.db.entity.AccountEntity

@Database(entities = [AccountEntity::class], version = 1)
abstract class AccountDataBase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {

        private const val TABLE_NAME = "account_entity"

        @Volatile
        private var INSTANCE: AccountDataBase? = null

        fun getInstance(context: Context): AccountDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AccountDataBase::class.java,
                TABLE_NAME
            )
                .build()
    }
}