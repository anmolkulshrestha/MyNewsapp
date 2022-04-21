package com.newsapp.newsapiclient.presentation.di

import android.app.Application
import com.newsapp.newsapiclient.domain.usecase.*
import com.newsapp.newsapiclient.presentation.viewmodel.Newsviewmodelfactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Factorymodule {
    @Singleton
    @Provides
  fun provideNewsViewModelFactory(application: Application, getnewsheadlinesusecase: Getnewsheadlinesusecase,
        getsearchednewsusecase: Getsearchednewsusecase, savenewsusecase: Savenewsusecase,
        getsavednewsusecase: Getsavednewsusecase, deletesavednewsusecase: Deletesavednewsusecase
  ):Newsviewmodelfactory{
      return Newsviewmodelfactory(application, getnewsheadlinesusecase, getsearchednewsusecase, savenewsusecase, getsavednewsusecase, deletesavednewsusecase
      )
  }

}








