package com.example.demowebview.ui

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.demowebview.Constants
import java.util.regex.Pattern

class NewsWebViewClient(
    private var isLayerTwo: Boolean = false,
    private var listener: OnClickCallback
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val urlRequest = request?.url.toString().replaceAfter("#", "").replace("#", "")
        if (isLayerTwo) {
            return when {
                urlRequest.endsWith(".html") -> {
                    listener.htmlCallback(urlRequest)
                    true
                }
                isContainPattern(urlRequest) -> {
                    false
                }
                else -> {
                    listener.browserCallback(urlRequest)
                    true
                }
            }
        } else {
            return if (urlRequest == (Constants.BASE_URL + "/")) {
                false
            } else {
                listener.layerOneCallback(urlRequest)
                true
            }
        }
    }

    private fun isContainPattern(url: String): Boolean {
        val regex = "\\b${Constants.BASE_URL}\\/\\w+"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(url)
        return matcher.find()
    }
}
