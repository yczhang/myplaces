package com.example.myplaces.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchResult (
    @PrimaryKey(autoGenerate = true)
    val table_id: Int = 0,
    @ColumnInfo(name = "keyword")
    val keyword:String?,
    @ColumnInfo(name = "value")
    val value:String?,
    @ColumnInfo(name = "timestamp")
    val timestamp:String?
    )
