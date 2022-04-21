package com.newsapp.newsapiclient.data.repository.dataSource

import com.newsapp.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

interface Newslocaldata {
    suspend fun savearticletodb(article: Article)
    fun getsavedarticles(): Flow<List<Article>>
    suspend fun deletearticlesfromdb(article: Article)


}