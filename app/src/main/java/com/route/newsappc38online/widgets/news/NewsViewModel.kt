package com.route.newsappc38online.widgets.news


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entities.SourceItemDTO
import com.route.domain.repos.NewsRepository
import com.route.domain.repos.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// MVI (Model-View-Intent) ---> Add On UI-Architecture Pattern
@HiltViewModel
class NewsViewModel @Inject constructor(
    val sourcesRepository: SourcesRepository,
    val newsRepository: NewsRepository,

    ) : ViewModel(), NewsContracts.ViewModel {
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
    private val _states = mutableStateOf<NewsContracts.State>(NewsContracts.State.Idle)
    override val states: MutableState<NewsContracts.State>
        get() = _states
    private val _events = mutableStateOf<NewsContracts.Event>(NewsContracts.Event.Idle)

    override val events: MutableState<NewsContracts.Event>
        get() = _events

    override fun handleActions(actions: NewsContracts.Actions) {
        when (actions) {
            is NewsContracts.Actions.GetNewsSources -> {
                getNewsSources(category = actions.categoryID) {
                    handleActions(NewsContracts.Actions.GetNewsArticles(it))
                }
            }

            is NewsContracts.Actions.GetNewsArticles -> {
                val sourceItemDTO = SourceItemDTO(id = actions.sourceID)
                getNewsBySource(sourceItemDTO)
            }

            is NewsContracts.Actions.ClickedOnNewsArticlesItem -> {
                // todo :
            }

        }
    }


    // Dependency Injection :- (SOLI'D' Dependency Inversion )
    // (Dagger) Hilt

    val selectedIndex = mutableIntStateOf(0)
    val sourcesList = mutableStateOf<List<SourceItemDTO>>((listOf()))
    fun getNewsBySource(
        sourceItem: SourceItemDTO,
    ) {
        _states.value = NewsContracts.State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = newsRepository.getNewsData(sourceId = sourceItem.id)
                _states.value = NewsContracts.State.Loaded(sourcesList.value, newsList = result)
            } catch (e: Exception) {
                _states.value = NewsContracts.State.Error(e.localizedMessage ?: "Error Occurred")
            }
        }
    }

    fun getNewsSources(category: String?, onSourceResponse: (String) -> Unit) {
        _states.value = NewsContracts.State.Loading
        viewModelScope.launch {
            try {

                val response = sourcesRepository.getSources(category ?: "")
                onSourceResponse(response.get(0).id)
                _states.value = NewsContracts.State.Loaded(response, listOf())
                sourcesList.value = response ?: listOf()
            } catch (ex: Exception) {
                _states.value = NewsContracts.State.Error(ex.localizedMessage ?: "Error Occurred")
            }
        }
    }
}