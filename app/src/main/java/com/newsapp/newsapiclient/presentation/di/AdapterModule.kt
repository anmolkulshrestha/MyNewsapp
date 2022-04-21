package com.newsapp.newsapiclient.presentation.di

import com.newsapp.newsapiclient.presentation.adapter.Newsadapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
   @Singleton
   @Provides
   fun provideNewsAdapter():Newsadapter{

       return Newsadapter()
   }
}