package com.newsapp.newsapiclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.newsapp.newsapiclient.databinding.ActivityMainBinding
import com.newsapp.newsapiclient.presentation.adapter.Newsadapter
import com.newsapp.newsapiclient.presentation.viewmodel.Newsviewmodel
import com.newsapp.newsapiclient.presentation.viewmodel.Newsviewmodelfactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: Newsviewmodelfactory
    @Inject
    lateinit var newsadapter: Newsadapter
    lateinit var viewModel: Newsviewmodel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvNews.setupWithNavController(navController)

        viewModel = ViewModelProvider(this,factory).get(Newsviewmodel::class.java)
    }
}