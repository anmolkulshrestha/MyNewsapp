package com.newsapp.newsapiclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsapp.newsapiclient.domain.usecase.*

class Newsviewmodelfactory(private val app:Application, private val getnewsheadlinesusecase: Getnewsheadlinesusecase,
    private val getsearchednewsusecase: Getsearchednewsusecase, private val savenewsusecase: Savenewsusecase,
    private val getsavednewsusecase: Getsavednewsusecase, private val deletesavednewsusecase: Deletesavednewsusecase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Newsviewmodel(
            app, getnewsheadlinesusecase, getsearchednewsusecase, savenewsusecase,
            getsavednewsusecase, deletesavednewsusecase) as T
    }
}









