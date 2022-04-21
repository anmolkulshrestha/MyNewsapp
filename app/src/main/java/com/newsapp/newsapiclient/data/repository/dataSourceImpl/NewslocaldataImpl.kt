package com.newsapp.newsapiclient.data.repository.dataSourceImpl

import com.newsapp.newsapiclient.data.db.ArticleDAO
import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.data.repository.dataSource.Newslocaldata
import kotlinx.coroutines.flow.Flow

class NewslocaldataImpl(private val articleDAO: ArticleDAO) : Newslocaldata {
    override suspend fun savearticletodb(article: Article) {
        articleDAO.insert(article) }



    override fun getsavedarticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles() }



    override suspend fun deletearticlesfromdb(article: Article) {
        articleDAO.deleteArticle(article) }
}