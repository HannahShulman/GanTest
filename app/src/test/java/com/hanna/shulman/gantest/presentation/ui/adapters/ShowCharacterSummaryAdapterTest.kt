package com.hanna.shulman.gantest.presentation.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.MockData
import com.hanna.shulman.gantest.R
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.presentation.ui.adapters.ShowCharacterSummaryAdapter
import com.hanna.shulman.gantest.presentation.ui.adapters.ShowCharacterSummaryViewHolder
import com.hanna.shulman.gantest.utils.BindableViewHolder
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ShowCharacterSummaryAdapterTest {
    private val adapter = ShowCharacterSummaryAdapter { }
    lateinit var view: View

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        view = LayoutInflater.from(context)
            .inflate(R.layout.single_show_character_item, FrameLayout(context), false)
    }

    @Test
    fun `GIVEN viewHolder WHEN adapter binds view holder THEN holder bind called`() {
        val mockViewHolder: BindableViewHolder<ShowCharacterSummary> = mock()
        adapter.submitList(listOf(MockData.showCharacterSummary))
        adapter.onBindViewHolder(mockViewHolder, 0)

        verify(mockViewHolder).bind(any())
    }

    @Test
    fun `GIVEN character WHEN bind to viewHolder THEN image is loaded with correct url`() {
        val viewHolder = ShowCharacterSummaryViewHolder(view) { }
        val spiedHelper = spy(viewHolder.glideHelper)

        viewHolder.glideHelper = spiedHelper

        viewHolder.bind(showCharacter)

        val urlCapture = argumentCaptor<String>()
        verify(spiedHelper).loadImage(urlCapture.capture(), any())
        assertThat(urlCapture.firstValue).isEqualTo("characterImgUrl")
    }

    @Test
    fun `GIVEN character WHEN bind to viewHolder THEN name is displayed`() {
        val viewHolder = ShowCharacterSummaryViewHolder(view) { }

        viewHolder.bind(showCharacter)

        assertThat(viewHolder.characterName.text).isEqualTo("characterName")
    }

    private val showCharacter = ShowCharacterSummary(
        charId = 23,
        name = "characterName",
        img = "characterImgUrl",
        seasonAppearance = emptyList()
    )
}