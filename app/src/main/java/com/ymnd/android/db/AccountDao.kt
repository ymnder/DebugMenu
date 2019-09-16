package com.ymnd.android.db

import androidx.room.*
import com.ymnd.android.db.entity.AccountEntity

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(account: AccountEntity)

    @Query("SELECT * FROM AccountEntity WHERE id = 0")
    suspend fun getAccount(): AccountEntity

    @Update
    suspend fun update(account: AccountEntity)

    @Delete
    suspend fun delete(account: AccountEntity)
}

