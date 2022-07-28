package com.example.demowebview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.demowebview.Constants
import com.example.demowebview.R
import com.example.demowebview.addFragment
import com.example.demowebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        addFragment(R.id.frameLayoutNews, LayerOneFragment.newInstance(Constants.BASE_URL), false, supportFragmentManager)
    }
}
