package br.com.lno.skillmeter.di

import android.content.Context
import androidx.room.Room
import br.com.lno.skillmeter.model.repository.SkillDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModuleTest {

    @Provides
    @Named("test_db")
    fun provideInMemoryDatabase(@ApplicationContext context: Context) =
        Room
            .inMemoryDatabaseBuilder(context, SkillDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}