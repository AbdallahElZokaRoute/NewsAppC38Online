package com.route.newsappc38online.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc38online.Constants
import com.route.newsappc38online.api.model.Category

@Composable
fun CategoriesContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Pick your category \n" +
                    "of interest", style = TextStyle(color = Color(0XFF4F5A69), fontSize = 16.sp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(6) {
                val item = Constants.categories.get(it)
                CategoryCard(item = item, position = it)
            }
        }


    }
}

@Composable
fun CategoryCard(item: Category, position: Int) {
    Card(
        colors = CardDefaults.cardColors(),
        modifier = Modifier
            .background(
                colorResource(id = item.backgroundColor),
                if (position % 2 == 0) RoundedCornerShape(
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
            )
    ) {
        Image(painter = painterResource(id = item.drawableResId), contentDescription = "")
        Text(
            stringResource(id = item.titleResID),
            style = TextStyle(color = Color.White, fontSize = 18.sp),
        )
    }
}