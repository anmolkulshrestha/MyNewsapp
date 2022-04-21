package com.newsapp.newsapiclient.domain.usecase

import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.domain.repository.Newsrepo
import kotlinx.coroutines.flow.Flow

class Getsavednewsusecase(private val newsrepo: Newsrepo) {
    fun getsavednews(): Flow<List<Article>>{
        return newsrepo.getsavednews() }
}