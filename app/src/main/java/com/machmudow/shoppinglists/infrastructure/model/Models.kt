package com.machmudow.shoppinglists.infrastructure.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String
)