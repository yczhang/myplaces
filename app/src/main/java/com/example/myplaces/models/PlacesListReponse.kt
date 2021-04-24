package com.example.myplaces.viewmodels

import com.squareup.moshi.Json

data class PlacesListReponse (
    val results : List<PlaceItem>? = null
        )

data class PlaceItem (
    @Json(name= "name")
    val name : String ?,
    @Json(name="icon")
    val icon : String ?,
    @Json(name="rating")
    val rating: Double ?
        )