package com.example.myplaces

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.myplaces.viewmodels.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.manipulation.Ordering
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyPlacesUnitTest {

    private lateinit var viewModel : MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this);

        viewModel = Mockito.mock(MainViewModel::class.java)
    }

    @Test
    fun TestDataProcessing()
    {
        viewModel.loadDataLocally(R.raw.sample)

        val items = viewModel.getItems()

        assert(items.size == 20)

        for ( item in items) {
            item.name?.let { Log.d("UnitTest", it) }
        }

    }
}