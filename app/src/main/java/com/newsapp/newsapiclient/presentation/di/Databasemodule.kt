package com.newsapp.newsapiclient.presentation.di

import android.app.Application
import androidx.room.Room
import com.newsapp.newsapiclient.data.db.ArticleDAO
import com.newsapp.newsapiclient.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Databasemodule {
    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(app, ArticleDatabase::class.java, "news_db").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(articleDatabase: ArticleDatabase): ArticleDAO {

        return articleDatabase.getArticleDAO()
    }


}