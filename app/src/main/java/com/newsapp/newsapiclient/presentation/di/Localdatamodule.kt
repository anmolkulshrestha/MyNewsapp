package com.newsapp.newsapiclient.presentation.di

import com.newsapp.newsapiclient.data.db.ArticleDAO
import com.newsapp.newsapiclient.data.repository.dataSource.Newslocaldata
import com.newsapp.newsapiclient.data.repository.dataSourceImpl.NewslocaldataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Localdatamodule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO):Newslocaldata{


        return NewslocaldataImpl(articleDAO)
    }

}













