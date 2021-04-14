package com.hanna.shulman.gantest.presentation.ui

import android.os.Build
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.MockData
import com.hanna.shulman.gantest.R
import com.hanna.shulman.gantest.presentation.viewmodels.CharacterByIdViewModel
import com.hanna.shulman.gantest.utils.images.ImageLoader
import com.hanna.shulman.gantest.utils.replace
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class CharacterByIdFragmentTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: FragmentScenario<CharacterByIdFragment>

    private val characterFlow = MutableStateFlow(
        MockData.showCharacter
    )

    val mockViewModel: CharacterByIdViewModel = mock {
        on { selectedCharacter } doReturn characterFlow
    }

    @Before
    fun setup() {
        val factory = CharacterByIdFragmentFactory(mockViewModel)
        scenario = FragmentScenario.launchInContainer(
            CharacterByIdFragment::class.java, null,
            R.style.Theme_AppCompat_NoActionBar, factory
        )
    }

    @Test
    fun `GIVEN a character WHEN fragment loaded THEN nickname is displayed`() {
        characterFlow.value = MockData.showCharacter

        scenario.onFragment {
            val nicknameValue = it.view!!.findViewById<TextView>(R.id.nickname_value)
            assertThat(nicknameValue.text).isEqualTo("Nickname")
        }
    }

    @Test
    fun `GIVEN a character WHEN data loaded THEN status is displayed`() {
        characterFlow.value = MockData.showCharacter

        scenario.onFragment {
            val statusValue = it.view!!.findViewById<TextView>(R.id.status_value)
            assertThat(statusValue.text).isEqualTo("Alive")
        }
    }

    @Test
    fun `GIVEN a character WHEN data loaded THEN name is displayed`() {
        characterFlow.value = MockData.showCharacter

        scenario.onFragment {
            val name = it.view!!.findViewById<TextView>(R.id.name)
            assertThat(name.text).isEqualTo("name")
        }
    }

    @Test
    fun `GIVEN a character WHEN data loaded THEN occupation is displayed`() {
        characterFlow.value = MockData.showCharacter

        scenario.onFragment {
            val occupationValue = it.view!!.findViewById<TextView>(R.id.occupation_value)
            assertThat(occupationValue.text).isEqualTo("Teacher")
        }
    }

    @Test
    fun `GIVEN a character WHEN data loaded THEN season appearance is displayed`() {
        characterFlow.value = MockData.showCharacter

        scenario.onFragment {
            val seasonAppearanceValue =
                it.view!!.findViewById<TextView>(R.id.season_appearance_value)
            assertThat(seasonAppearanceValue.text).isEqualTo("1,3,4")
        }
    }

    @Test
    fun `GIVEN a character WHEN data loaded THEN image is loaded`() {
        characterFlow.value = MockData.showCharacter

        scenario.onFragment {
            verify(it.imageLoaderHelper).loadImage(any(), any())
        }
    }
}

class CharacterByIdFragmentFactory(private val mockViewModel: CharacterByIdViewModel) :
    FragmentFactory() {

    @ExperimentalCoroutinesApi
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return CharacterByIdFragment.newInstance(3).apply {
            replace(CharacterByIdFragment::viewModel, mockViewModel)
            imageLoaderHelper = mock<ImageLoader> { }
        }
    }
}