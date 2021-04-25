package com.example.myplaces.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myplaces.BuildConfig
import com.example.myplaces.api.PlacesListAPI
import com.example.myplaces.database.PlaceRoomDatabase
import com.example.myplaces.database.PlacesRepository
import com.example.myplaces.database.SearchResult
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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDateTime

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MainViewModel"
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val coroutineDatabaseScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private var repository: PlacesRepository? = null

    private var results :  List<PlaceItem> ? = null
    private var history :  List<SearchResult> ? = null

    private val _isDataReady = MutableLiveData<Boolean>()
    val isDataReady: LiveData<Boolean>
        get() = _isDataReady

    private val _isHistoryReady = MutableLiveData<Boolean>()
    val isHistoryReady: LiveData<Boolean>
        get() = _isHistoryReady

    var range: Double = 10.0

    private lateinit var currentLocation: LatLng

    private var retried = false

    init {

        application.applicationContext ?.let {

            val placeDao = PlaceRoomDatabase.getDatabase(it,coroutineDatabaseScope).placeDao()

            repository = PlacesRepository(placeDao)
        }
    }

    fun loadDataLocally(path:String) {

    }

    fun loadHistory() {
        coroutineDatabaseScope.launch {
            history = repository?.getAll()

            coroutineScope.launch {
                _isHistoryReady.value = true
            }
        }
    }

    fun loadPlacesFromHistory(index:Int) {

        coroutineDatabaseScope.launch {

            val item = repository?.getItem(index)

            coroutineScope.launch {

                item?.let {

                    results = Gson().fromJson(it.value, Array<PlaceItem>::class.java).toList()

                    _isDataReady.value = !(results == null)

                } ?: run {
                        _isDataReady.value = false
                }
            }
        }
    }
    fun searchPlaces(keyword:String) {
        coroutineScope.launch {

            val locationStr = "${currentLocation.latitude},${currentLocation.longitude}"
            var rangeStr = "${range * 1625 as Int}"

            if(retried) rangeStr = "${range * 1625 * 2 as Int}"

            val request = PlacesListAPI.retrofitService.fetchplaces(keyword, BuildConfig.BASE_KEY,locationStr,rangeStr)

            try {
                val result = request.await()

                if (result.isSuccessful) {

                    results = result.body()?.results

                    if (results?.isEmpty() == true) {

                        if(retried ) {
                            Log.d(TAG,"Retrying...")
                            retried = false
                            _isDataReady.value = false
                        } else {
                            retried = true
                            Log.e(TAG,"searchPlaces Retry")
                            searchPlaces(keyword)
                        }

                    } else {
                        saveSearchingResult(keyword)
                        _isDataReady.value = true
                    }

                    Log.d(TAG,"Count: ${results?.size} ")

                } else {
                    _isDataReady.value = false
                    retried = false
                    Log.e(TAG, "Failed")
                }
            } catch (e:Exception) {
                _isDataReady.value = false
                retried = false
                Log.e(TAG, e.localizedMessage)
            }

        }
    }

    fun getItems() : List<PlaceItem> {
        return results ?: listOf()
    }

    fun getHistory() : List<SearchResult> {

        return history ?: listOf()
    }
    fun onRangeChanged(text: CharSequence) {

        if (text.isNotEmpty()) {
            range = text.toString().toDouble()
            if (range <= 0) range = 10.0
        }
    }

    fun setCurrentLocation(lat:Double, lon:Double) {
        currentLocation = LatLng(lat,lon)
    }

    private fun saveSearchingResult(keyword:String) {

        coroutineDatabaseScope.launch {

            results?.let {

                val current = LocalDateTime.now()
                var gson = Gson()
                var jsonString = gson.toJson(it)

                val newRecord = SearchResult(0, keyword, jsonString, current.toString())
                repository?.insert(newRecord)
            }
        }
    }

    fun onHistoryListClick(index: Int) {
        Log.d(TAG, "History Clicked ${index}")
    }
}