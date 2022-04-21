package com.newsapp.newsapiclient.domain.usecase

import com.newsapp.newsapiclient.data.model.APIResponse
import com.newsapp.newsapiclient.data.util.Resource
import com.newsapp.newsapiclient.domain.repository.Newsrepo

class Getnewsheadlinesusecase(private val newsrepo: Newsrepo) {

    suspend fun getnews(country : String, page : Int): Resource<APIResponse>{
        return newsrepo.getnewsheadlinesfromapi(country,page) }
}