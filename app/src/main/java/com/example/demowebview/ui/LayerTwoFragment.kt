package com.example.demowebview.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.demowebview.BaseFragment
import com.example.demowebview.R
import com.example.demowebview.databinding.FragmentLayerTwoBinding

class LayerTwoFragment : BaseFragment<FragmentLayerTwoBinding>(FragmentLayerTwoBinding::inflate) {
    private var newsUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsUrl = it.getString(BUNDLE_URL).toString()
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
