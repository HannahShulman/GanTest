package com.hanna.shulman.gantest._di

import android.content.Context
import androidx.room.Room
import com.hanna.shulman.gantest.data.datasource.db.AppDb
import com.hanna.shulman.gantest.data.datasource.db.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext appContext: Context): AppDb =
        Room.databaseBuilder(appContext, AppDb::class.java, "app-db").build()

    @Provides
    @Singleton
    fun provideDao(db: AppDb): CharacterDao = db.ordersDao()
}