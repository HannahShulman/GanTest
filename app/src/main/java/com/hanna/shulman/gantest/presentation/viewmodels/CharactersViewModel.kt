package com.hanna.shulman.gantest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.data.datasource.Resource
import com.hanna.shulman.gantest.data.datasource.Status
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.domain.usecases.FilterShowCharacters
import com.hanna.shulman.gantest.domain.usecases.GetCharactersSummary
import com.hanna.shulman.gantest.presentation.ui.ViewStates
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharactersFilter(
    val filterNameSeq: String = "",
    val filterEpisodes: List<Int> = emptyList()
)

@OpenForTesting
@ExperimentalCoroutinesApi
class CharactersViewModel(
    val getCharactersSummary: GetCharactersSummary,
    val filterShowCharacters: FilterShowCharacters
) : ViewModel() {

    private final val charactersFilter = MutableStateFlow<CharactersFilter?>(null)

    var selectedEpisodes = listOf<Int>()

    private final val charactersSummaryListResource: Flow<Resource<List<ShowCharacterSummary>>> =
        charactersFilter.flatMapLatest {
            return@flatMapLatest if (it == null) {
                getCharactersSummary()
            } else {
                return@flatMapLatest filterShowCharacters(it).map { list ->
                    Resource.success(list)
                }
            }

        }

    val charactersSummaryList =
        charactersSummaryListResource.map { it.data.orEmpty().sortedBy { it.name } }

    val state = charactersSummaryListResource.map {
        when (it.status) {
            Status.LOADING -> ViewStates.State.LOADING.takeIf { _ -> it.data.isNullOrEmpty() }
                ?: ViewStates.State.MAIN
            Status.SUCCESS -> ViewStates.State.MAIN
            Status.ERROR -> {
                ViewStates.State.ERROR.takeIf { _ -> it.data.isNullOrEmpty() }
                    ?: ViewStates.State.MAIN
            }
        }
    }

    fun getEpisodesRage(list: List<ShowCharacterSummary>): EpisodesRange {
        val firstEps = list.map {
            it.seasonAppearance.minOrNull() ?: 1
        }.minOrNull() ?: 1
        val lastEps = list.map {
            it.seasonAppearance.maxOrNull() ?: 1
        }.maxOrNull() ?: 1
        return EpisodesRange(firstEps, lastEps)
    }

    fun setCharacterFilter(filter: CharactersFilter?) {
        viewModelScope.launch {
            charactersFilter.emit(filter)
            selectedEpisodes = filter?.filterEpisodes.orEmpty()
        }
    }
}

@ExperimentalCoroutinesApi
class CharactersViewModelFactory @Inject constructor(
    private val getCharactersSummary: GetCharactersSummary,
    private val filterShowCharacters: FilterShowCharacters
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(getCharactersSummary, filterShowCharacters) as T
    }
}