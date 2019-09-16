package com.ymnd.android.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    @PrimaryKey
    val id: Int = 0,
    val status: String
)