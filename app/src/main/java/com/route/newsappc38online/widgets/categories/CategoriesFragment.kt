package com.route.newsappc38online.widgets.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.route.newsappc38online.Category
import com.route.newsappc38online.Constants
import com.route.newsappc38online.widgets.news.NEWS_ROUTE

val CATEGORIES_ROUTE = "categories"

@Composable
fun CategoriesContent(navHostController: NavHostController) {
    // Categories + News List + MVVM UI-Architecture Pattern
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Pick your category \n" +
                    "of interest",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(color = Color(0XFF4F5A69), fontSize = 22.sp)
        )
        // Sports -> 1- Navigate To News Fragment
        //          2- Get News Sources Corresponding To Sports (Category)
        //          3- Get News  Corresponding To News Sources (Tabs)
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(Constants.categories.size) {
                val item = Constants.categories.get(it)
                CategoryCard(item = item, position = it, navHostController)
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(item: Category, position: Int, navHostController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = item.backgroundColor)),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        onClick = {
            navHostController.navigate("$NEWS_ROUTE/${item.apiID}")
        },
        shape = if (position % 2 == 0) RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 0.dp
        ) else
            RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp,
                bottomStart = 0.dp
            )

    ) {
        Image(
            painter = painterResource(id = item.drawableResId), contentDescription = "",

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(80.dp),
            contentScale = ContentScale.FillHeight //in XML : scaleType  fitXY
        )
        Text(
            stringResource(id = item.titleResID),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            style = TextStyle(color = Color.White, fontSize = 18.sp),
        )
    }
}