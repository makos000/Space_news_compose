package com.example.space_news_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import com.example.space_news_compose.room.ArticleEntity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleScreen(viewModel: MainViewModel, onClicked: () -> Unit) {
    val list = viewModel.data.collectAsState().value.data

    val dbList = viewModel.readArticle

    if (dbList != null) {
        if (dbList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nothing here...")
            }
        } else {
            LazyColumn(){
                itemsIndexed(items = dbList){
                        index, item ->
                    Card(onClick = { onClicked.invoke()
                        viewModel.article = item.spaceModel
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = item.spaceModel.title, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                            Text(text = item.spaceModel.newsSite, fontStyle = FontStyle.Italic, fontSize = 17.sp)
                            Text(text = item.spaceModel.summary, fontSize = 17.sp)
                        }
                    }
                }
            }
        }
    }
}