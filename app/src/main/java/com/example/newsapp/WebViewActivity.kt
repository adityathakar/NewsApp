package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.model.Article
import com.example.newsapp.ui.theme.NewsAppTheme

class WebViewActivity : ComponentActivity() {

    private val article: Article by lazy {
        intent?.getSerializableExtra(ARTICLE) as Article
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Adding a WebView inside AndroidView
                    // with layout as full screen
                    AndroidView(factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = WebViewClient()
                            loadUrl(article.articleUrl)
                        }
                    }, update = {
                        it.loadUrl(article.articleUrl)
                    })
                }
            }
        }
    }

    companion object {
        private const val ARTICLE = "article"
        fun newIntent(context: Context, article: Article) =
            Intent(context, WebViewActivity::class.java).apply {
                putExtra(ARTICLE, article)
            }
    }
}