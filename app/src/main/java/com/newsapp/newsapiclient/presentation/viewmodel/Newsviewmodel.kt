package com.newsapp.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.newsapp.newsapiclient.data.model.APIResponse
import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.data.util.Resource
import com.newsapp.newsapiclient.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class Newsviewmodel(private val app:Application, private val getnewsheadlinesusecase: Getnewsheadlinesusecase, private val getsearchednewsusecase: Getsearchednewsusecase,
    private val savenewsusecase: Savenewsusecase, private val getsavednewsusecase: Getsavednewsusecase,
    private val deletesavednewsusecase: Deletesavednewsusecase) : AndroidViewModel(app) {
    val newsheadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsheadlines.postValue(Resource.Loading())
        try{ if(isNetworkAvailable(app)) {
          val apiResult = getnewsheadlinesusecase.getnews(country, page)
          newsheadlines.postValue(apiResult) }
      else{ newsheadlines.postValue(Resource.Error("Internet is not available")) } }
        catch (e:Exception){ newsheadlines.postValue(Resource.Error(e.message.toString())) } }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }


    val searchednews : MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    fun searchNews(searchQuery : String, page: Int) = viewModelScope.launch {
       searchednews.postValue(Resource.Loading())
        try { if (isNetworkAvailable(app)) {
            val response = getsearchednewsusecase.getsearchednews(searchQuery, page)
                searchednews.postValue(response)
            } else {
                searchednews.postValue(Resource.Error("No internet connection"))
            }
        }catch(e:Exception){
            searchednews.postValue(Resource.Error(e.message.toString()))
        }
    }


    fun saveArticle(article: Article) = viewModelScope.launch {
        savenewsusecase.savenews(article)
    }

    fun getSavedNews() = liveData{
        getsavednewsusecase.getsavednews().collect {
            emit(it)
        }
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        deletesavednewsusecase.deletenews(article)
    }

}














