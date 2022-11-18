package com.example.space_news_compose.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.space_news_compose.app.theme.Space_news_composeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

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



