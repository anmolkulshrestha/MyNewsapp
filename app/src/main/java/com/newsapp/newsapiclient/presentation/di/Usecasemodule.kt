package com.newsapp.newsapiclient.presentation.di

import com.newsapp.newsapiclient.domain.repository.Newsrepo
import com.newsapp.newsapiclient.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Usecasemodule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(newsrepo: Newsrepo):Getnewsheadlinesusecase{

      return Getnewsheadlinesusecase(newsrepo) }

   @Singleton
   @Provides
   fun provideGetSearchedNewsUseCase(newsrepo: Newsrepo):Getsearchednewsusecase{


      return Getsearchednewsusecase(newsrepo) }

   @Singleton
   @Provides
   fun provideSaveNewsUseCase(newsrepo: Newsrepo):Savenewsusecase{


      return Savenewsusecase(newsrepo) }

   @Singleton
   @Provides
   fun provideGetSavedNewsUseCase(newsrepo: Newsrepo):Getsavednewsusecase{


      return Getsavednewsusecase(newsrepo) }

   @Singleton
   @Provides
   fun provideDeleteSavedNewsUseCase(newsrepo: Newsrepo):Deletesavednewsusecase{

      return Deletesavednewsusecase(newsrepo) }
}


















