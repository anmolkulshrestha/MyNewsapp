package com.newsapp.newsapiclient.domain.usecase

import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.domain.repository.Newsrepo

class Savenewsusecase(private val newsrepo: Newsrepo) {
  suspend fun savenews(article: Article)=newsrepo.savenews(article) }