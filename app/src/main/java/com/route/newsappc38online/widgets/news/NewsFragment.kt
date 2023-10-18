package com.route.newsappc38online.widgets.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.domain.entities.ArticlesItemDTO
import com.route.domain.entities.SourceItemDTO
import com.route.newsappc38online.R

const val NEWS_ROUTE = "news"

// MVVM Architecture Pattern

@Composable
fun RenderStates(viewModel: NewsViewModel = hiltViewModel()) {
    val state = viewModel.states.value
    when (state) {
        is NewsContracts.State.Loading -> {
            NewsCircularLoading()
        }

        is NewsContracts.State.Error -> {
            NewsErrorDialog(errorMessage = state.message)
        }

        is NewsContracts.State.Loaded -> {
            Column {
                NewsSourcesTabs(sourcesItemsList = state.sourcesList)
                NewsList(state.newsList)
            }
        }

        is NewsContracts.State.Idle -> {
            // Initial State
        }
    }
    val event = viewModel.events.value
    when (event) {
        is NewsContracts.Event.Idle -> {

        }

        is NewsContracts.Event.NavigateToArticleDetails -> {

        }
    }
}

@Composable
fun NewsCircularLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.colorGreen))
    }
}

@Composable
fun NewsErrorDialog(errorMessage: String) {
    AlertDialog(onDismissRequest = { }, confirmButton = {
        Button(onClick = {

        }) {
            Text(text = "OK")
        }
    }, title = {
        Text(text = errorMessage)
    })
}

@Composable
fun NewsFragment(
    category: String?,
    viewModel: NewsViewModel = hiltViewModel() // Lifecycle-Aware
) {
    viewModel.handleActions(NewsContracts.Actions.GetNewsSources(category ?: ""))
    RenderStates()
}

@Composable
fun NewsList(articlesList: List<ArticlesItemDTO>) {
    LazyColumn {
        items(articlesList.size) {
            NewsCard(articlesItem = articlesList.get(it))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItemDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),

        ) {
        GlideImage(
            model = articlesItem.urlToImage ?: "",
            contentDescription = "News Picture",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        Text(
            text = articlesItem.author ?: "",
            style = TextStyle(color = colorResource(id = R.color.grey))
        )
        Text(
            text = articlesItem.title ?: "",
            style = TextStyle(colorResource(id = R.color.colorBlack))
        )
        Text(
            text = articlesItem.publishedAt ?: "",

            modifier = Modifier.align(Alignment.End),
            style = TextStyle(
                color = colorResource(id = R.color.grey2),
            )
        )
    }
}

@Composable
fun NewsSourcesTabs(
    modifier: Modifier = Modifier,
    sourcesItemsList: List<SourceItemDTO>,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    // MVVM


    if (sourcesItemsList.isNotEmpty())
        ScrollableTabRow(
            selectedTabIndex = viewModel.selectedIndex.intValue, containerColor = Color.Transparent,
            divider = {},
            indicator = {},
            modifier = modifier
        ) {
            sourcesItemsList.forEachIndexed { index, sourceItem ->
                Tab(
                    selected = viewModel.selectedIndex.intValue == index,
                    onClick = {
                        viewModel.selectedIndex.intValue = index
                        viewModel.handleActions(NewsContracts.Actions.GetNewsArticles(sourceID = sourceItem.id))
                    },

                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xFF39A552),
                    modifier = if (viewModel.selectedIndex.intValue == index)
                        Modifier
                            .padding(end = 2.dp)
                            .background(
                                Color(0xFF39A552),
                                RoundedCornerShape(50)
                            )
                    else
                        Modifier
                            .padding(end = 2.dp)
                            .border(2.dp, Color(0xFF39A552), RoundedCornerShape(50)),
                    text = { Text(text = sourceItem.name ?: "") }
                )


            }

        }

}

@Preview(name = "News Card", showSystemUi = true)
@Composable
fun NewsCardPreview() {
    NewsCard(
        articlesItem = ArticlesItemDTO(
            publishedAt = "10 / 9 / 2023",
            title = "BBC News",
            urlToImage = "URL To Image",
            //LoremIpsum(15).toString(), title = "Title ", content = LoremIpsum(20).toString()

        )
    )
}

