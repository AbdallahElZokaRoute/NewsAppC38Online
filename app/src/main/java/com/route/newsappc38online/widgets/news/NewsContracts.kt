package com.route.newsappc38online.widgets.news

import androidx.compose.runtime.MutableState
import com.route.domain.entities.ArticlesItemDTO
import com.route.domain.entities.SourceItemDTO

// Events- > When ViewModel Wants to send data to Fragment/Activity
// States- > When ViewModel Wants to send data to Fragment/Activity

// Actions -> When Activity/Fragment wants to send Action To ViewModel

class NewsContracts {

    interface ViewModel {
        val states: MutableState<State>
        val events: MutableState<Event>
        fun handleActions(actions: Actions)
    }

    sealed interface Event {
        object Idle : Event
        object NavigateToArticleDetails : Event


    }

    sealed class State {
        object Idle : State()
        object Loading : State()
        data class Error(val message: String) : State()
        data class Loaded(
            val sourcesList: List<SourceItemDTO>,
            val newsList: List<ArticlesItemDTO>
        ) : State()

    }

    sealed interface Actions {
        class GetNewsSources(val categoryID: String) : Actions
        class GetNewsArticles(val sourceID: String) : Actions
        class ClickedOnNewsArticlesItem(val articlesItemDTO: ArticlesItemDTO) : Actions

    }

    enum class WeekDays(val position: Int) {
        SAT(0),
        SUN(1),
        MON(2),
        TUE(3),
        WED(4),
        THU(5),
        FRI(6)
    }


    sealed interface APIStates {
        object Loading : APIStates
        class Loaded(val list: List<SourceItemDTO>) : APIStates
        class Error(val message: String) : APIStates

    }

    val day2 = WeekDays.SUN

}
