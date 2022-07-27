package com.example.demowebview.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.example.demowebview.BaseFragment
import com.example.demowebview.R
import com.example.demowebview.addFragment
import com.example.demowebview.databinding.FragmentLayerTwoBinding

class LayerTwoFragment : BaseFragment<FragmentLayerTwoBinding>(FragmentLayerTwoBinding::inflate), OnClickCallback {
    private var newsUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsUrl = it.getString(BUNDLE_URL).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebViewData()
    }

    override fun tabsCallback(urlRequest: String) {
        activity?.let {
            addFragment(
                R.id.frameLayoutNews,
                newInstance(urlRequest),
                true,
                it.supportFragmentManager
            )
        }
    }

    override fun htmlCallback(urlRequest: String) {
        activity?.let {
            addFragment(
                R.id.frameLayoutNews,
                newInstance(urlRequest),
                true,
                it.supportFragmentManager
            )
        }
    }

    override fun browserCallback(urlRequest: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(urlRequest)).apply {
            context?.startActivity(this)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewData() {
        binding?.webViewLayerTwo?.apply {
            loadUrl(newsUrl)
            settings.javaScriptEnabled = true
            webViewClient = NewsWebViewClient(this@LayerTwoFragment)
        }
    }

    companion object {
        private const val BUNDLE_URL = "BUNDLE_URL"

        @JvmStatic
        fun newInstance(url: String) =
            LayerTwoFragment().apply {
                arguments = bundleOf(BUNDLE_URL to url)
            }
    }
}
