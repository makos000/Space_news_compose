package com.example.space_news_compose.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.space_news_compose.data.remote.api.ApiInterface
import com.example.space_news_compose.data.remote.remoteds.RemoteDataSourceImpl
import com.example.space_news_compose.data.repo.FakeRepo
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepo: FakeRepo

    private val spaceModelItem = SpaceModelItem()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remote: RemoteDataSourceImpl

    @Mock
    private lateinit var api: ApiInterface

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        fakeRepo = FakeRepo(remote)
        viewModel = MainViewModel(fakeRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `api call test loading and success`() = runBlocking {
        whenever(remote.getData()).thenReturn(Resource.Success(arrayListOf(spaceModelItem)))
        viewModel.getData()
        viewModel.data.test {
            assertTrue(awaitItem() is Resource.Loading)
            awaitItem()
            val result = viewModel.data.value.data?.get(0)?.spaceModel
            assertEquals(awaitItem().data?.get(0)?.spaceModel , result)
            cancel()
        }
    }

    @Test
    fun `api call test loading and error`() = runBlocking {
        whenever(api.getData()).thenReturn(Response.error(404,"Error".toResponseBody()))
        whenever(remote.getData()).thenReturn(Resource.Error("Error"))
        viewModel.getData()
        viewModel.data.test {
            assertTrue(awaitItem() is Resource.Loading)
            awaitItem()
            val result = viewModel.data.value.message
            assertEquals(awaitItem().message , result)
            cancel()
        }
    }
}