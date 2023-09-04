package com.route.newsappc38online

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc38online.ui.theme.NewsAppC38OnlineTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC38OnlineTheme {
                // A surface container using the 'background' color from the theme
                // Handler main Thread
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2500)
                // Coroutines

                SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {
    Column(
        modifier = Modifier
            .fillMaxSize() // scaleType in xml
            .paint(
                painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            ), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo",
            modifier = Modifier.fillMaxSize(0.45F)
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.35F))
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = "App Signature",
            modifier = Modifier
                .fillMaxSize(0.5F)
        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NewsAppC38OnlineTheme {
        SplashContent()
    }
}