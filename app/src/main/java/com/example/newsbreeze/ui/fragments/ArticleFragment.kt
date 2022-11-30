package com.example.newsbreeze.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsbreeze.NewsViewModel
import com.example.newsbreeze.R
import com.example.newsbreeze.ui.NewsBreezeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article){
    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsBreezeActivity).viewModel
        val article = args.article

        toolbar_layout.title = article.title
        Glide.with(this).load(article.urlToImage).into(imgBackground)
        tvAuthorName.text = article.author
        tvAuthorDesc.text = article.source?.name

        tvArticleDesc.text = article.description
        tvArticleContent.text = article.content

        btnSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
        imgBtnSave.setOnClickListener {
            btnSave.callOnClick()
        }



    }
}