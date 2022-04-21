package com.newsapp.newsapiclient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.newsapiclient.databinding.FragmentNewsBinding
import com.newsapp.newsapiclient.presentation.adapter.Newsadapter
import com.newsapp.newsapiclient.presentation.viewmodel.Newsviewmodel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {
    private  lateinit var viewModel: Newsviewmodel
    private lateinit var newsadapter1: Newsadapter
    private lateinit var newsadapter2: Newsadapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "us"
    private var page = 1
    private var isscrolling = false
    private var isloading = false
    private var islastpage = false
    private var pages = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel= (activity as MainActivity).viewModel
        newsadapter1= (activity as MainActivity).newsadapter
        newsadapter1.setOnItemClickListener {
          val bundle = Bundle().apply {
             putSerializable("selected_article",it)
          }
          findNavController().navigate(
              R.id.action_newsFragment_to_infoFragment,
              bundle
          )
        }
        newsadapter2= Newsadapter()
        newsadapter2.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
        }

        initRecyclerViewhori()
        initRecyclerViewverti()
        viewNewsList()
        setSearchView()
    }

    private fun viewNewsList() {

        viewModel.getNewsHeadLines(country,page)
        viewModel.newsheadlines.observe(viewLifecycleOwner,{response->
            when(response){
               is com.newsapp.newsapiclient.data.util.Resource.Success->{
                   hideProgressBar()
                   response.data?.let { newsadapter1.differ.submitList(it.articles.toList())
                         if(it.totalResults%20 == 0) { pages = it.totalResults / 20 }
                         else{ pages = it.totalResults/20+1 }
                         islastpage = page == pages }
               }
                is com.newsapp.newsapiclient.data.util.Resource.Error->{
                   hideProgressBar()
                   response.message?.let {
                       Toast.makeText(activity,"An error occurred : $it", Toast.LENGTH_LONG).show()
                   }
                }

                is com.newsapp.newsapiclient.data.util.Resource.Loading->{
                    showProgressBar()
                }

            }
        })
    }

    private fun initRecyclerViewhori() {

        fragmentNewsBinding.rvNews.apply {
            adapter = newsadapter1
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }
    private fun initRecyclerViewverti() {

        fragmentNewsBinding.yournewsrecyclerview.apply {
            adapter = newsadapter2
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }
    private fun showProgressBar(){
        isloading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        isloading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }
    private val onScrollListener = object : RecyclerView.OnScrollListener(){
       override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
           super.onScrollStateChanged(recyclerView, newState)
           if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
               isscrolling = true
           }

       }
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
           super.onScrolled(recyclerView, dx, dy)
           val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
           val sizeOfTheCurrentList = layoutManager.itemCount
           val visibleItems = layoutManager.childCount
           val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
           val shouldPaginate = !isloading && !islastpage && hasReachedToEnd && isscrolling
           if(shouldPaginate){
               page++
               viewModel.getNewsHeadLines(country,page)
               isscrolling = false

           }


       }
   }


   private fun setSearchView(){
     fragmentNewsBinding.svNews.setOnQueryTextListener(
         object : SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(p0: String?): Boolean {
                 viewModel.searchNews(p0.toString(),page)
                 viewSearchedNews()
                 return false
             }

             override fun onQueryTextChange(p0: String?): Boolean {
                 MainScope().launch {
                     delay(2000)
                     viewModel.searchNews( p0.toString(), page)
                     viewSearchedNews()
                 }
                 return false
             }

         })

         fragmentNewsBinding.svNews.setOnCloseListener(
             object :SearchView.OnCloseListener{
                 override fun onClose(): Boolean {
                     initRecyclerViewverti()

                     return false
                 }

             })
   }




   fun viewSearchedNews(){
       viewModel.searchednews.observe(viewLifecycleOwner,{response->
           when(response){
               is com.newsapp.newsapiclient.data.util.Resource.Success->{

                   hideProgressBar()
                   response.data?.let {
                       Log.i("MYTAG","came here ${it.articles.toList().size}")
                       newsadapter2.differ.submitList(it.articles.toList())
                       if(it.totalResults%20 == 0) {
                           pages = it.totalResults / 20
                       }else{
                           pages = it.totalResults/20+1
                       }
                       islastpage = page == pages
                   }
               }
               is com.newsapp.newsapiclient.data.util.Resource.Error->{
                   hideProgressBar()
                   response.message?.let {
                       Toast.makeText(activity,"An error occurred : $it", Toast.LENGTH_LONG).show()
                   }
               }

               is com.newsapp.newsapiclient.data.util.Resource.Loading->{
                   showProgressBar()
               }

           }
       })
   }

}
















