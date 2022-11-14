package com.example.space_news_compose.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.space_news_compose.model.SpaceModelItem
import com.example.space_news_compose.ui.theme.Space_news_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Space_news_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
    @Composable
    fun MyApp() {
        var showMain by rememberSaveable { mutableStateOf(viewModel.mainScreen) }

        if (showMain) {
            viewModel.getData()
            viewModel.readDatabase()
            ArticleScreen(viewModel = viewModel, onClicked = {viewModel.mainScreen = false
            showMain = viewModel.mainScreen})
        } else {
            DetailScreen(onClicked = {viewModel.mainScreen = true
                showMain = viewModel.mainScreen}, viewModel = viewModel)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Space_news_composeTheme {
            MyApp()
        }
    }
}



