package com.example.space_news_compose.data.repo

import com.example.space_news_compose.data.local.room.ArticleEntity
import com.example.space_news_compose.data.remote.remoteds.RemoteDataSourceImpl
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RepoImplTest {

    private lateinit var fakeRepo: FakeRepo
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    private val spaceModelItem = SpaceModelItem()

    @Mock
    private lateinit var remote: RemoteDataSourceImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        fakeRepo = FakeRepo(remote)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `simulate success response from api call`() = runBlocking{
        val expectedResult = Resource.Success(arrayListOf<SpaceModelItem>())
        whenever(remote.getData()).thenReturn(expectedResult)

        val response = fakeRepo.getDataFromAPI()

        assertEquals(response, expectedResult)
    }

    @Test
    fun `get list from fakerepo with model after insertArticlesToDB`() = runBlocking{
        val expected = listOf(spaceModelItem)
        fakeRepo.insertArticlesToDB(ArticleEntity(spaceModelItem))
        assertEquals(expected,fakeRepo.list)
    }

    @Test
    fun `get empty list of AppEntity from flow collect of fake repo`() = runBlocking{
        val expected = listOf<ArticleEntity>()
        fakeRepo.insertArticlesToDB(ArticleEntity(spaceModelItem))
        val result = fakeRepo.readArticlesFromDB()
        result.collect(){
            assertEquals(expected,it)
        }
    }

    @Test
    fun `get list with model and then nuke it, expect emtpy list`() = runBlocking{
        val expected = listOf(spaceModelItem)
        fakeRepo.insertArticlesToDB(ArticleEntity(spaceModelItem))
        fakeRepo.nukeTable()
        val result = fakeRepo.readArticlesFromDB()
        result.collect(){
            assertEquals(expected,it)
        }
    }
}