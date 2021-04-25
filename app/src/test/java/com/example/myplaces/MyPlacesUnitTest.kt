package com.example.myplaces

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myplaces.viewmodels.MainViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyPlacesUnitTest {

    @Mock
    private lateinit var mockContext: Context
    private lateinit var viewModel: MainViewModel
    private lateinit var application: MainApplication

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mockContext = Mockito.mock(Context::class.java)
        application = Mockito.mock(MainApplication::class.java)
        viewModel = MainViewModel(application)
    }

    @Test
    fun TestDataProcessing()
    {
        val inputStream = javaClass.classLoader?.getResourceAsStream("sample.json")

        val jsonStr = inputStream?.bufferedReader().use {
            it?.readText()
        }

        viewModel.loadDataLocally(jsonStr)

        val items = viewModel.getItems()

        assert(items.size == 19)

        for ( item in items) {
            item.name?.let { Log.d("UnitTest", it) }
        }

    }
}