package com.newsapp.newsapiclient.presentation.di

import com.newsapp.newsapiclient.data.api.NewsAPI
import com.newsapp.newsapiclient.data.repository.dataSource.Newsremotedata
import com.newsapp.newsapiclient.data.repository.dataSourceImpl.NewsremotedataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Remotedatamodule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPI: NewsAPI
    ):Newsremotedata{

        return NewsremotedataImpl(newsAPI)
    }

}












