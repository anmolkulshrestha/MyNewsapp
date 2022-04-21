package com.newsapp.newsapiclient

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.newsapp.newsapiclient.databinding.FragmentBlankBinding


class WrittingFragment : Fragment() {

lateinit var binding: FragmentBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankBinding.bind(view)
        val args : WrittingFragmentArgs by navArgs()
        val article = args.selectedarticle


        binding.editText.setText(article.title.toString())

       binding.sharetought.setOnClickListener {
           var intent = Intent(Intent.ACTION_SEND)
           intent.type="text/plain"
           intent.putExtra(Intent.EXTRA_TEXT,binding.editText.text.toString())
           startActivity(intent)
       }





    }
}