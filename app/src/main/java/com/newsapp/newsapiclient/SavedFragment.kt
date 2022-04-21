package com.newsapp.newsapiclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.newsapiclient.databinding.FragmentSavedBinding
import com.newsapp.newsapiclient.presentation.adapter.Newsadapter
import com.newsapp.newsapiclient.presentation.viewmodel.Newsviewmodel
import com.google.android.material.snackbar.Snackbar


class SavedFragment : Fragment() {
    private lateinit var fragmentSavedBinding: FragmentSavedBinding
    private lateinit var viewModel: Newsviewmodel
    private lateinit var newsadapter: Newsadapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSavedBinding = FragmentSavedBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsadapter = (activity as MainActivity).newsadapter
        newsadapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it) }
            findNavController().navigate(R.id.action_savedFragment_to_infoFragment, bundle) }
               initRecyclerView()
          viewModel.getSavedNews().observe(viewLifecycleOwner,{
            newsadapter.differ.submitList(it)
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { val position = viewHolder.adapterPosition
                val article = newsadapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view,"Deleted Successfully",Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){ viewModel.saveArticle(article) }
                        show() } }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(fragmentSavedBinding.rvSaved) } }
    private fun initRecyclerView(){
      fragmentSavedBinding.rvSaved.apply {
          adapter = newsadapter
          layoutManager = LinearLayoutManager(activity)
      }
    }







}