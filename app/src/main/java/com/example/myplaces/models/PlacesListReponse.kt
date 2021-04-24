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
    val rating: Double ?,
    @Json(name="photos")
    val photos:List<PlacePhoto>?
        )

data class PlacePhoto(
    @Json(name="height")
    val height : Int?,
    @Json(name="photo_reference")
    val photo_reference:String?
)