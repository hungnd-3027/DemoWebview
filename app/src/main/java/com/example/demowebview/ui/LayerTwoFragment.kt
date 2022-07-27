package com.example.demowebview.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.os.bundleOf
import com.example.demowebview.BaseFragment
import com.example.demowebview.R
import com.example.demowebview.addFragment
import com.example.demowebview.databinding.FragmentLayerTwoBinding

class LayerTwoFragment : BaseFragment<FragmentLayerTwoBinding>(FragmentLayerTwoBinding::inflate),
    OnClickCallback, View.OnClickListener {

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
        registerOnclick()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.imageBack -> {
                binding?.webViewLayerTwo?.apply {
                    if (canGoBack()) goBack()
                }
            }
            R.id.imageClose -> {
                closeAllLayerTwo()
            }
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
            webViewClient = NewsWebViewClient(true, this@LayerTwoFragment)
            setOnKeyListener { view, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {
                    goBack()
                } else {
                    activity?.onBackPressed()
                }
                true
            }
        }
    }

    private fun closeAllLayerTwo() {
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.backStackEntryCount?.let {
            for (i in 0 until (fragmentManager.backStackEntryCount)) {
                fragmentManager.popBackStack()
            }
        }
    }

    private fun registerOnclick() {
        binding?.apply {
            imageBack.setOnClickListener(this@LayerTwoFragment)
            imageClose.setOnClickListener(this@LayerTwoFragment)
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
