package com.example.myplaces.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myplaces.BuildConfig
import com.example.myplaces.api.PlacesListAPI
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MainViewModel"
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var results :  List<PlaceItem> ? = null

    private val _isDataReady = MutableLiveData<Boolean>()
    val isDataReady: LiveData<Boolean>
        get() = _isDataReady

    init {

    }
    fun searchPlaces(keyword:String) {

        coroutineScope.launch {

            val request = PlacesListAPI.retrofitService.fetch(keyword, BuildConfig.BASE_KEY,"-33.8670522,151.1957362","1500")

            try {
                val result = request.await()

                if (result.isSuccessful) {

                    results = result.body()?.results

                    _isDataReady.value = true

                    Log.d(TAG,"Count: ${results?.size} ")

                } else {
                    _isDataReady.value = false
                    Log.e(TAG, "Failed")
                }
            } catch (e:Exception) {
                _isDataReady.value = false
                Log.e(TAG, e.localizedMessage)
            }

        }
    }

    fun getItems() : List<PlaceItem>
    {
        return results ?: listOf()
    }
}