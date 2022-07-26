package com.example.demowebview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demowebview.Constants
import com.example.demowebview.R
import com.example.demowebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.frame_layout_news, LayerOneFragment.newInstance(Constants.BASE_URL))
            addToBackStack(null)
            commit()
        }
    }
}
