package com.hanna.shulman.gantest._di

import com.hanna.shulman.gantest.data.repositories.impl.CharactersRepositoryImpl
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun orderRepository(repository: CharactersRepositoryImpl): CharactersRepository
}