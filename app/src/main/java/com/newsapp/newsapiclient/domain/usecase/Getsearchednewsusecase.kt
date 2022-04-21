package com.newsapp.newsapiclient.domain.usecase

import com.newsapp.newsapiclient.data.model.APIResponse
import com.newsapp.newsapiclient.data.util.Resource
import com.newsapp.newsapiclient.domain.repository.Newsrepo

class Getsearchednewsusecase(private val newsrepo: Newsrepo) {
     suspend fun getsearchednews(searchQuery:String,page:Int): Resource<APIResponse>{
         return newsrepo.getsearchednewsfromapi(searchQuery,page) }
}