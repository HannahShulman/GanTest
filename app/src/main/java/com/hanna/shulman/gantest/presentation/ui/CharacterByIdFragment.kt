package com.hanna.shulman.gantest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.R
import com.hanna.shulman.gantest.databinding.FragmentCharacterByIdBinding
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.presentation.viewmodels.CharacterByIdViewModel
import com.hanna.shulman.gantest.presentation.viewmodels.CharacterByIdViewModelFactory
import com.hanna.shulman.gantest.utils.images.ImageLoader
import com.hanna.shulman.gantest.utils.provideViewModel
import com.hanna.shulman.gantest.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//TESTS-V
@ExperimentalCoroutinesApi
@AndroidEntryPoint
@OpenForTesting
class CharacterByIdFragment : Fragment(R.layout.fragment_character_by_id) {

    @Inject
    lateinit var factory: CharacterByIdViewModelFactory

    @Inject
    lateinit var imageLoaderHelper: ImageLoader

    @ExperimentalCoroutinesApi
    val viewModel: CharacterByIdViewModel by provideViewModel { factory }

    private val viewStates: ViewStates by lazy {
        ViewStates(requireView())
    }

    private val binding: FragmentCharacterByIdBinding by viewBinding(FragmentCharacterByIdBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStates.setState(ViewStates.State.LOADING)

        lifecycleScope.launch {
            viewModel.selectedCharacter.collect {
                bindData(it)
            }
        }

        arguments?.getInt(CHAR_ID_KEY)?.let {
            viewModel.setSelectedId(it)
        }
    }

    private fun bindData(character: ShowCharacter) {
        binding.name.text = character.name
        imageLoaderHelper.loadImage(character.img, binding.characterImage)
        binding.nicknameValue.text = character.nickname
        binding.occupationValue.text = character.occupation.joinToString(",")
        binding.statusValue.text = character.status.value
        binding.seasonAppearanceValue.text = character.seasonAppearance.joinToString(",")
    }

    companion object {
        private const val CHAR_ID_KEY = "char_id_key"
        fun newInstance(characterId: Int) =
            CharacterByIdFragment().apply {
                arguments = bundleOf(CHAR_ID_KEY to characterId)
            }
    }
}