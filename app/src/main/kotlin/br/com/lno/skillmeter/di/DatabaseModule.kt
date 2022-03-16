package br.com.lno.skillmeter.di

import android.content.Context
import androidx.room.Room
import br.com.lno.skillmeter.model.repository.SkillDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideSkillDao(skillDatabase: SkillDatabase) = skillDatabase.skillDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SkillDatabase::class.java, "SKILL_DATABASE.db")
            .fallbackToDestructiveMigration().build()
}