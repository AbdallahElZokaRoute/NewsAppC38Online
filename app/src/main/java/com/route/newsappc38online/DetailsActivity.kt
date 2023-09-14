package com.route.newsappc38online

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsappc38online.api.model.ArticlesItem

class DetailsActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val articlesItem = intent.getSerializableExtra("article", ArticlesItem::class.java)
            ShowArticleDetails(articlesItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShowArticleDetails(articlesItem: ArticlesItem?) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
            .padding(vertical = 10.dp)

    ) {
        Column {
            GlideImage(
                model = articlesItem?.urlToImage ?: "",
                contentDescription = "News Picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), contentScale = ContentScale.FillBounds
            )
            Text(
                text = articlesItem?.author ?: "",
                style = TextStyle(color = colorResource(id = R.color.grey)),
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
            Text(
                text = articlesItem?.title ?: "",
                style = TextStyle(colorResource(id = R.color.colorBlack)),
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
            Text(
                text = articlesItem?.content ?: "",
                style = TextStyle(colorResource(id = R.color.colorBlack)),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 5.dp, horizontal = 10.dp)

            )
            Text(
                text = articlesItem?.publishedAt ?: "",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(vertical = 5.dp, horizontal = 10.dp),
                style = TextStyle(
                    color = colorResource(id = R.color.grey2),
                )
            )
            TextButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(articlesItem?.url)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF303030)
                ),
                modifier = Modifier.align(Alignment.End)

            ) {
                Text(text = "View Full Article")
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF303030)
                )
            }
        }
    }

}

@Composable
fun AppBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

    }
}