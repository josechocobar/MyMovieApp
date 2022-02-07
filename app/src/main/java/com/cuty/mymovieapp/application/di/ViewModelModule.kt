package com.cuty.mymovieapp.application.di

import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.local.AppDatabase
import com.cuty.mymovieapp.data.local.LocalDataSource
import com.cuty.mymovieapp.data.local.LocalDataSourceInterface
import com.cuty.mymovieapp.data.remote.RemoteDataSource
import com.cuty.mymovieapp.data.remote.RemoteDataSourceInt
import com.cuty.mymovieapp.data.remote.TimeController.TimeControl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRepoImpl(dataSource: RemoteDataSourceInt,localDataSource: LocalDataSource):RepoImplementation{
        return RepoImplementation(dataSource,localDataSource)
    }
    @Provides
    @ViewModelScoped
    fun bindDataSourceImpl():RemoteDataSourceInt{
        return RemoteDataSource()
    }
    @Provides
    @ViewModelScoped
    fun bindLocalDataSource(appDatabase: AppDatabase):LocalDataSourceInterface{
        return LocalDataSource(appDatabase)
    }
    @Provides
    @ViewModelScoped
    fun bindTimeControl():TimeControl{
        return TimeControl()
    }
}