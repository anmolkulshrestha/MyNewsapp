package com.newsapp.newsapiclient.presentation.di

import com.newsapp.newsapiclient.data.repository.NewsrepoImpl
import com.newsapp.newsapiclient.data.repository.dataSource.Newslocaldata
import com.newsapp.newsapiclient.data.repository.dataSource.Newsremotedata
import com.newsapp.newsapiclient.domain.repository.Newsrepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Repositorymodule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsremotedata: Newsremotedata, newslocaldata: Newslocaldata
    ): Newsrepo { return NewsrepoImpl(newsremotedata, newslocaldata) }

}














