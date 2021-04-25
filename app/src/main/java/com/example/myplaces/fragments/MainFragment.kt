package com.example.myplaces.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplaces.R
import com.example.myplaces.databinding.MainFragmentBinding
import com.example.myplaces.viewmodels.MainViewModel
import com.example.myplaces.views.PlacesListAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var binding : MainFragmentBinding

    private val TAG = "MainFragment"

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        viewModel =  ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        binding.svKeyword.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchPlaces(query)
                binding.svKeyword.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })

        activity ?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }

        obtainLocation()

        setupObservers()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun setupObservers()
    {
        viewModel.isDataReady.observe(viewLifecycleOwner, Observer {

            if (it) {
                binding.rvList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter =
                        PlacesListAdapter(
                            viewModel.getItems()
                        )
                }

                binding.svKeyword.clearFocus()
            }
            else {
                Toast.makeText(requireContext(), "Places Search Failed.  Please check your network connection, and try again", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun obtainLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient ?.let {

            it.lastLocation.addOnSuccessListener { location ->
                if (location != null) {

                    viewModel.setCurrentLocation(location.latitude, location.longitude)
                }
            }
            it.lastLocation.addOnFailureListener { it ->
            }
        }
    }

}