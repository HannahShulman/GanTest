package com.hanna.shulman.gantest.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter

@Database(entities = [ShowCharacter::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun ordersDao(): CharacterDao
}