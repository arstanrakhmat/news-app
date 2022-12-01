package com.example.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.models.Article
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()
    private var isArticleSave: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        clickListeners(article)
    }

    private fun clickListeners(article: Article) {
        binding.back.setOnClickListener { navigateUp() }

        binding.fab.apply {

            if (article.isArchived) {
                setImageResource(R.drawable.ic_saved_article)
            } else {
                setImageResource(R.drawable.ic_not_saved_article)
            }


            setOnClickListener {
                if (article.isArchived) {
                    viewModel.deleteArticle(article)
//                    setImageResource(R.drawable.ic_saved_article)

                    Toast.makeText(
                        requireContext(),
                        "Article is restored from cash",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.saveArticle(
                        Article(
                            article.id,
                            article.author,
                            article.content,
                            article.description,
                            article.publishedAt,
                            article.source,
                            article.title,
                            article.url,
                            article.urlToImage,
                            true
                        )
                    )
                    Toast.makeText(requireContext(), "Article is saved", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }
}