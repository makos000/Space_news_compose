package com.example.space_news_compose.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.space_news_compose.data.local.ArticleEntity
import com.example.space_news_compose.data.remote.RemoteDataSourceImpl
import com.example.space_news_compose.data.remote.api.ApiInterface
import com.example.space_news_compose.data.repo.FakeRepo
import com.example.space_news_compose.data.repo.RepoInterface
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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

    /*@Test
    fun `api call test`() = runBlocking {
        viewModel.getData()
        viewModel.data.test {
            assertTrue(awaitItem() is Resource.Loading)
            val result = viewModel.data.value.data?.get(0)?.spaceModel
            assertEquals(awaitItem().data, result)
        }
    }*/
}