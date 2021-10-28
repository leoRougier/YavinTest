package com.example.yavintest.di

import android.content.Context
import com.example.yavintest.data.db.YavinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        YavinDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: YavinDatabase) = db.ticketDao()
}