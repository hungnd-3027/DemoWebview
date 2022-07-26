package com.example.demowebview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.demowebview.BaseFragment
import com.example.demowebview.databinding.FragmentLayerOneBinding

class LayerOneFragment : BaseFragment<FragmentLayerOneBinding>(FragmentLayerOneBinding::inflate) {
    private var baseUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            baseUrl = it.getString(BUNDLE_BASE_URL).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebViewData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewData() {
        binding?.webViewLayerOne?.apply {
            loadUrl(baseUrl)
            settings.javaScriptEnabled = true
        }
    }

    companion object {
        private const val BUNDLE_BASE_URL = "BUNDLE_BASE_URL"

        @JvmStatic
        fun newInstance(baseUrl: String) =
            LayerOneFragment().apply {
                arguments = bundleOf(BUNDLE_BASE_URL to baseUrl)
            }
    }
}