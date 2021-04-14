package com.hanna.shulman.gantest.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.hanna.shulman.gantest.R
import com.hanna.shulman.gantest.databinding.FragmentCharactersListBinding
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.presentation.ui.adapters.ShowCharacterSummaryAdapter
import com.hanna.shulman.gantest.presentation.viewmodels.CharactersFilter
import com.hanna.shulman.gantest.presentation.viewmodels.CharactersViewModel
import com.hanna.shulman.gantest.presentation.viewmodels.CharactersViewModelFactory
import com.hanna.shulman.gantest.utils.provideViewModel
import com.hanna.shulman.gantest.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {

    @Inject
    lateinit var factory: CharactersViewModelFactory

    @ExperimentalCoroutinesApi
    private val viewModel: CharactersViewModel by provideViewModel { factory }

    private val viewStates: ViewStates by lazy {
        ViewStates(requireView())
    }

    private val binding: FragmentCharactersListBinding by viewBinding(FragmentCharactersListBinding::bind)

    private val characterAdapter = ShowCharacterSummaryAdapter(::onCharacterSelected)

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.charactersList) {
            adapter = characterAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        binding.searchName.addTextChangedListener {
            viewModel.setCharacterFilter(buildFilter())
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                viewStates.setState(state)
                if (state == ViewStates.State.ERROR) {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong, please try later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.charactersSummaryList.collect { data ->
                bindData(data)
            }
        }

        viewModel.setCharacterFilter(null)
    }


    private fun bindData(list: List<ShowCharacterSummary>) {
        characterAdapter.submitList(list)
        inflateEpisodes(list)
    }

    //this can/should be extracted to a component or so.
    private fun inflateEpisodes(list: List<ShowCharacterSummary>) {
        val episodesRange = viewModel.getEpisodesRage(list)
        binding.episodeContainer.removeAllViews()
        for (i in episodesRange.first..episodesRange.second) {
            val view = CheckBox(requireContext()).apply {
                text = i.toString()
                isChecked = viewModel.selectedEpisodes.contains(i)
                setOnCheckedChangeListener { _, _ ->
                    viewModel.setCharacterFilter(buildFilter())
                }
            }
            binding.episodeContainer.addView(view)
        }
    }

    private fun buildFilter(): CharactersFilter {
        val searchName = binding.searchName.text.toString()
        val episodes: List<Int> =
            binding.episodeContainer.children.filter { (it as CheckBox).isChecked }.map {
                (it as CheckBox).text.toString().toInt()
            }.toList()
        return CharactersFilter(searchName, episodes)
    }

    private fun onCharacterSelected(character: ShowCharacterSummary) {
        requireActivity().supportFragmentManager.commit {
            addToBackStack(null)
            add(R.id.container, CharacterByIdFragment.newInstance(character.charId))
        }
    }
}