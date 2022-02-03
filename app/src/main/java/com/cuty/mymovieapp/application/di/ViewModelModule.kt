package com.cuty.mymovieapp.application.di

import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.local.LocalDatabaseDao
import com.cuty.mymovieapp.data.remote.RemoteDataSource
import com.cuty.mymovieapp.data.remote.RemoteDataSourceInt
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
    fun bindRepoImpl(dataSource: RemoteDataSourceInt,localDatabaseDao: LocalDatabaseDao):RepoImplementation{
        return RepoImplementation(dataSource,localDatabaseDao)
    }
    @Provides
    @ViewModelScoped
    fun bindDataSourceImpl():RemoteDataSourceInt{
        return RemoteDataSource()
    }

}