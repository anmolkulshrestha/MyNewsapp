package com.newsapp.newsapiclient.domain.usecase

import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.domain.repository.Newsrepo

class Deletesavednewsusecase(private val newsrepo: Newsrepo) {
    suspend fun deletenews(article: Article)=newsrepo.deletenews(article)
}