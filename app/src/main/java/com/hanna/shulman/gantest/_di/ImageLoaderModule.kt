package com.hanna.shulman.gantest._di

import com.hanna.shulman.gantest.utils.images.GlideHelper
import com.hanna.shulman.gantest.utils.images.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object ImageLoaderModule {

    @Provides
    fun provideImageLoader(): ImageLoader = GlideHelper()
}