package com.example.myplaces.database

import com.example.myplaces.viewmodels.PlaceItem

class PlacesRepository (private val placeDao: PlaceDao){
    fun insertAll(items: List<SearchResult>){
        placeDao.insertAll(items)
    }

    fun insert(item: SearchResult){
        placeDao.insert(item)
    }

    fun getAll(): List<SearchResult> {
        return placeDao.getAll()
    }

    fun getItem(index:Int) : SearchResult ? {

       return placeDao.getItem(index)

    }

    fun dellAll()
    {
        placeDao.deleteAll()
    }
}