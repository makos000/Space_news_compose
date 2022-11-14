package com.example.space_news_compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailScreen(onClicked: () -> Unit, viewModel: MainViewModel) {
    Surface {
        Row() {
            IconButton(onClick = onClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back button",
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = viewModel.article.title, fontSize = 35.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Text(text = viewModel.article.newsSite, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Text(text = viewModel.article.summary, fontSize = 20.sp, fontWeight = FontWeight.Medium, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Image(
                painter = rememberAsyncImagePainter(viewModel.article.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
        }
    }
}