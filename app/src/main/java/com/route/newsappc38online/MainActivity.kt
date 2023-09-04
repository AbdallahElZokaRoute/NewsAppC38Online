package com.route.newsappc38online

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavigatorProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.newsappc38online.api.APIManager
import com.route.newsappc38online.api.model.SourceItem
import com.route.newsappc38online.api.model.SourceResponse
import com.route.newsappc38online.ui.theme.NewsAppC38OnlineTheme
import com.route.newsappc38online.widgets.CategoriesContent
import com.route.newsappc38online.widgets.DrawerBody
import com.route.newsappc38online.widgets.DrawerHeader
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    /***
     *
     *  API : "Application Programming Interface"
     *
     *  Facebook App (android - IOS ) ->   Facebook Server
     *
     *  Facebook Website (on Microsoft Edge) -> Facebook Server
     *
     *  Http Protocol (Hyper Text Transfer Protocol ):-
     *          Facebook App (android(Kotlin) - IOS(Swift) ) < ->   Facebook Server (ASP .Net )
     *                      JavaScript Object Notation
     *                          JSON
     *           {
     *              "name": "Abdallah",
     *              "age": 25,
     *              "friends": [{"name" : "MOhamed" , "age" : 30 }]
     *           }
     *
     *          Http Request
     *                          3 Seconds
     *                                              Http Response
     *         My Name:- Abdallah
     *                         3 Seconds
     *                                              Timeline for abdallah
     *
     *
     *  Recap :-
     *         ( Http Requests : Http Response )->
     *          POST and GET
     *          Postman (Get (Google .com))
     *          405 Method Not Allowed
     *          Http Status Codes
     *          100..199
     *
     *          Retrofit (Client Network Library )
     *              Retrofit Converters (
     *                V         X       X
     *              "GSON, Jackson", Simple XML  )
     *          GSON
     *
     *
     *
     *
     *
     */


    val API_KEY = "c027443ca9624422bfbe9b160b9ec11a"

    // Lazy vs Eager Initialization
    var myNumber = 0 // Eager
    val myNumberLazy by lazy {   // Create in Runtime 
        listOf("", "", "")
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC38OnlineTheme {
                // A surface container using the 'background' color from the theme
                var sourcesList: MutableState<List<SourceItem>> =
                    remember {
                        mutableStateOf(listOf()) // ["", ""]
                        // list [           ]
                    }
                APIManager
                    .getNewsServices()
                    .getNewsSources(API_KEY)
//                    .execute()
                    .enqueue(object : Callback<SourceResponse> {
                        override fun onResponse(
                            call: Call<SourceResponse>,
                            response: Response<SourceResponse>
                        ) {
                            val body = response.body()
                            Log.e("TAG", "onResponse: ${body?.status}")
                            Log.e("TAG", "onResponse: ${body?.sources}")
                            sourcesList.value = body?.sources ?: listOf()
                        }

                        override fun onFailure(call: Call<SourceResponse>, t: Throwable) {

                        }

                    }
                    ) // Run On Background Thread
//                    .execute() // Run On Main Thread
                // ANR (Application Not Responding  -> Wait - Kill )
                // Main Thread :- User Interaction
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(drawerContent = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        DrawerHeader()
                        DrawerBody()
                    }

                }, drawerState = drawerState) {
                    Scaffold(
                        topBar = { NewsAppBar(drawerState) }
                    ) {
//                        NewsSourcesTabs(sourcesItemsList = sourcesList.value)
                        // nav Controller -> Navigate
                        // Fragment A -> Fragment B

                        // B -> C
                        //   -> D
                        // NavController
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController, startDestination = "categories"
                        ) {
                            composable(route = "categories",) {
                                CategoriesContent()

                            }
                        }

                    }
                }

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NewsAppBar(drawerState: DrawerState) {
        val scope = rememberCoroutineScope()
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.news),
                    style = TextStyle(color = Color.White, fontSize = 22.sp)
                )
            },
            modifier = Modifier.clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 26.dp,
                    bottomEnd = 26.dp
                )
            ),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFF39A552),
                navigationIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Icon Navigation"
                    )

                }
            }
        )
    }

    // Recomposition
    @Composable
    fun NewsSourcesTabs(
        sourcesItemsList: List<SourceItem>
    ) {
        var selectedIndex3 = remember {
            mutableStateOf(0) //
        }
        var selectedIndex2 = remember {
            mutableStateOf(0)
        }
        var selectedIndex by remember {
            mutableStateOf(0)
        }


        if (sourcesItemsList.isNotEmpty())
            ScrollableTabRow(
                selectedTabIndex = selectedIndex, containerColor = Color.Transparent,
                divider = {},
                indicator = {},
            ) {
                sourcesItemsList.forEachIndexed { index, sourceItem ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color(0xFF39A552),
                        modifier = if (selectedIndex == index)
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

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun Preview() {
        NewsAppC38OnlineTheme {
            Scaffold(

            ) {

                NewsSourcesTabs(
                    sourcesItemsList = listOf(
                        SourceItem(name = "ABC News"),
                        SourceItem(name = "ABC News"),
                        SourceItem(name = "ABC News"),
                        SourceItem(name = "ABC News"),
                        SourceItem(name = "ABC News"),
                        SourceItem(name = "ABC News"),
                        SourceItem(name = "ABC News"),
                    )
                )
            }


        }
    }
}