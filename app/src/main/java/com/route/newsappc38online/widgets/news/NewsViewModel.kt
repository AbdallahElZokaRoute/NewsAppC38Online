package com.route.newsappc38online.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entities.ArticlesItemDTO
import com.route.domain.entities.SourceItemDTO
import com.route.domain.repos.NewsRepository
import com.route.domain.repos.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val sourcesRepository: SourcesRepository,
    val newsRepository: NewsRepository,

    ) : ViewModel() {
    // Kotlin - (Coroutines)  - Concept - Kotlin Flows

    // Clean Architecture :-
    // Presentation layer
    // Data Layer   (Data , Retrofit , Room , GSON (JSON))
    // Domain Layer (interfaces , Entities ,UseCases)

    // Repository Pattern (Architecture)
    /**
     *  1- retrofit X ->   Volley
     *
     *  2- Room X -> SqLite
     *
     *
     */
    val sourcesList = mutableStateOf<List<SourceItemDTO>>((listOf()))
    // Dependency Injection :- (SOLI'D' Dependency Inversion )
    // (Dagger) Hilt

    val selectedIndex = mutableIntStateOf(0)
    val newsList = mutableStateOf<List<ArticlesItemDTO>?>(listOf())
    fun getNewsBySource(
        sourceItem: SourceItemDTO,
        newsResponseState: MutableState<List<ArticlesItemDTO>?>
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            // Concurrency
            try {
                val response = newsRepository.getNewsData(sourceItem.id ?: "")
                withContext(Dispatchers.Main) {
                    newsResponseState.value = response
                }
            } catch (ex: Exception) {
                Log.e("Exception:", "${ex.message}")
            }
        }
    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<SourceItemDTO>>) {
        viewModelScope.launch {
            try {
                val response = sourcesRepository?.getSources(category ?: "")
                sourcesList.value = response ?: listOf()
            } catch (ex: Exception) {
                Log.e("Exception", "${ex.message}")
            }
        }
    }
}