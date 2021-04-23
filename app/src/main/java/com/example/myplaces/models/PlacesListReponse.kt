package com.example.myplaces.viewmodels

data class PlacesListReponse (
    val result : List<PlaceItem>? = null
        )

data class PlaceItem (
    val name : String ?,
    val image : String ?,
    val rating: Int ?
        )