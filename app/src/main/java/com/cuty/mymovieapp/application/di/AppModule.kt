package com.cuty.mymovieapp.application.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.cuty.mymovieapp.data.local.AppDatabase
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
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "movie_table"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(appDatabase:AppDatabase) = appDatabase.localDatabaseDao()

    @Singleton
    @Provides
    fun provideYourConnectivityManager(@ApplicationContext context: Context) = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context
}