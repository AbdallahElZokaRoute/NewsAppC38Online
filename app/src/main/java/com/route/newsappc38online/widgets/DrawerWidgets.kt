package com.route.newsappc38online.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc38online.R


@Composable
fun DrawerHeader() {
    Text(
        text = stringResource(id = R.string.news_app),
        modifier = Modifier
            .fillMaxWidth(0.6F)
            .fillMaxHeight(0.1F)
            .background(Color(0xFF39A552), shape = RoundedCornerShape(0.dp))
            .padding(vertical = 16.dp),
        style = TextStyle(
            color = Color.White, fontSize = 20.sp,
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun DrawerBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.6F)
            .fillMaxHeight()
            .background(Color.White)
            .padding(top = 16.dp)
    ) {
        NewsDrawerItem(iconId = R.drawable.ic_categories, textId = R.string.categories)
        NewsDrawerItem(iconId = R.drawable.ic_settings, textId = R.string.settings)
    }
}

@Composable
fun NewsDrawerItem(iconId: Int, textId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.6F)
            .background(Color.White)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Icon(painter = painterResource(id = iconId), contentDescription = "")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = textId), style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xFF303030)
            )
        )

    }
}
