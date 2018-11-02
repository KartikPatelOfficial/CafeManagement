package com.deucate.cafemanagement

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.annotation.TargetApi
import android.support.v7.app.AlertDialog
import com.mrgames13.jimdo.splashscreen.App.SplashScreenBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SplashScreenBuilder.getInstance(this)
            .setImage(R.mipmap.ic_launcher)
            .setVideo(R.raw.splash_animation)
            .setTitle("Cafe Management")
            .setSubtitle("Powerd by Matex Technology")
            .show()

        webView = findViewById(R.id.mainWebView)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                AlertDialog.Builder(this@MainActivity).setTitle("Error").setMessage(description).show()
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            override fun onReceivedError(view: WebView, req: WebResourceRequest, rerr: WebResourceError) {
                onReceivedError(view, rerr.errorCode, rerr.description.toString(), req.url.toString())
                AlertDialog.Builder(this@MainActivity).setTitle("Error").setMessage(rerr.description.toString()).show()
            }
        }
        webView.loadUrl("http://demo.matextechnology.com/cafe/login")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
