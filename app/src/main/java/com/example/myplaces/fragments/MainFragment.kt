package com.example.myplaces.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplaces.R
import com.example.myplaces.databinding.MainFragmentBinding
import com.example.myplaces.viewmodels.MainViewModel
import com.example.myplaces.views.PlacesListAdapter
import com.google.android.libraries.places.api.Places
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var binding : MainFragmentBinding

    private val TAG = "MainFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        viewModel =  ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        binding.svKeyword.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchPlaces(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })


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

}