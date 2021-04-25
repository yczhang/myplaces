package com.example.myplaces.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {

    @Query("SELECT * FROM search_table")
    fun getSearchist(): List<SearchResult>

    @Insert
    fun insert(item: SearchResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<SearchResult>)

    @Query("DELETE FROM search_table")
    fun deleteAll()
}