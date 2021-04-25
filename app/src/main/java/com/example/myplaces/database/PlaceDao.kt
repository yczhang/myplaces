package com.example.myplaces.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {

    @Query("SELECT * FROM search_table")
    fun getAll(): List<SearchResult>

    @Insert
    fun insert(item: SearchResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<SearchResult>)

    @Query("DELETE FROM search_table")
    fun deleteAll()

    @Query("SELECT * FROM search_table WHERE table_id = :index  LIMIT 1")
    fun getItem( index: Int): SearchResult
}