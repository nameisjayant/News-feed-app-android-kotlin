package com.codingwithjks.retrofit.Ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.codingwithjks.retrofit.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    private lateinit var url: String

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val bundle = intent.extras
        if (bundle != null) {
            url = bundle.getString("url").toString()
        }
        webview.loadUrl(url)
    }
}