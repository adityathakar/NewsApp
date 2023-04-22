package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            val onBackArrowClicked: () -> Unit = { finish() }

            NewsAppTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier
                                .clickable { onBackArrowClicked() }
                                .size(30.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                        Icon(
                            modifier = Modifier
                                .clickable { }
                                .size(30.dp),
                            imageVector = Icons.Default.FavoriteBorder, contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
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