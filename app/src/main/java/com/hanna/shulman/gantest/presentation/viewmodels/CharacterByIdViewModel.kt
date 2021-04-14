package com.hanna.shulman.gantest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.usecases.GetCharacterById
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias EpisodesRange = Pair<Int, Int>

@OpenForTesting
class CharacterByIdViewModel(val getCharacterById: GetCharacterById) : ViewModel() {
    final val selectedCharacterId = MutableSharedFlow<Int>()

    @ExperimentalCoroutinesApi
    val selectedCharacter: Flow<ShowCharacter> =
        selectedCharacterId.flatMapLatest { getCharacterById(it) }

    fun setSelectedId(charId: Int) {
        viewModelScope.launch {
            selectedCharacterId.emit(charId)
        }
    }
}


@ExperimentalCoroutinesApi
class CharacterByIdViewModelFactory @Inject constructor(
    private val getCharacterById: GetCharacterById
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterByIdViewModel(getCharacterById) as T
    }
}