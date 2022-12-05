package com.example.space_news_compose.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleScreen(viewModel: MainViewModel, onClicked: () -> Unit) {
    LaunchedEffect(true) {
        viewModel.getData()
    }

    val list = viewModel.data.collectAsState().value.data

    if (list != null){
        if (list.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "There is nothing to display...")
            }
        }else{
            LazyColumn {
                itemsIndexed(items = list){
                        index, item ->
                    Card(onClick = {onClicked.invoke()
                        viewModel.article = item.spaceModel
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 14.dp),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Column {
                            Row(Modifier.padding(top = 10.dp)) {
                                Card(Modifier
                                    .fillMaxWidth(),
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(22.dp)) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = item.spaceModel.imageUrl),
                                        modifier = Modifier.size(250.dp),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "Article Image")
                                }
                            }
                            Row(Modifier.padding(top = 10.dp, start = 8.dp)) {
                                Box(Modifier
                                    .fillMaxWidth()) {
                                    Text(text = item.spaceModel.title, fontSize = 24.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.W400)
                                }
                            }
                            Row(Modifier.padding(horizontal = 8.dp)) {
                                Box(Modifier
                                    .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                                ) {
                                    Column {
                                        Text(text = item.spaceModel.newsSite + " • " + item.spaceModel.publishedAt, fontSize = 16.sp, color = Color.Gray)
                                    }
                                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                                        IconButton(onClick = onClicked) {
                                            Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Favourite Button")
                                        }
                                    }
                                }
                            }

                        }
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}