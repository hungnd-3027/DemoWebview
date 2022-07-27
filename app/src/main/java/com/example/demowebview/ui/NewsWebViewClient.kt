package com.example.demowebview.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.demowebview.Constants
import java.util.regex.Pattern

class NewsWebViewClient(
    private var context: Context,
    private var callback: (String) -> Unit
) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val urlRequest = request?.url.toString().replaceAfter("#", "").replace("#", "")
        if (urlRequest == (Constants.BASE_URL+"/")) {
            return false
        } else {
            if (urlRequest.endsWith(".html")){
                callback(urlRequest)
                return true
            } else if (isContainPattern(urlRequest)) {
                callback(urlRequest)
                return true
            } else {
                Intent(Intent.ACTION_VIEW, Uri.parse(urlRequest)).apply {
                    context.startActivity(this)
                }
                return true
            }
        }

    }

    private fun isContainPattern(url: String) : Boolean {
        val regex = "\\b${Constants.BASE_URL}\\/\\w+\\-"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(url)
        return matcher.find()
    }
}