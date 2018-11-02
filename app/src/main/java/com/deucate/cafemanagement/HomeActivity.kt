package com.deucate.cafemanagement

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mrgames13.jimdo.splashscreen.App.SplashScreenBuilder
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var webView: WebView


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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
                AlertDialog.Builder(this@HomeActivity).setTitle("Error").setMessage(description).show()
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            override fun onReceivedError(view: WebView, req: WebResourceRequest, rerr: WebResourceError) {
                onReceivedError(view, rerr.errorCode, rerr.description.toString(), req.url.toString())
                AlertDialog.Builder(this@HomeActivity).setTitle("Error").setMessage(rerr.description.toString()).show()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_pos -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe")
            }
            R.id.nav_product -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe/products")
            }
            R.id.nav_store -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe/stores")
            }
            R.id.nav_sales -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe/sales")
            }
            R.id.nav_expanse -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe/expences")
            }
            R.id.nav_setting -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe/settings?tab=setting")
            }
            R.id.nav_report -> {
                webView.loadUrl("http://demo.matextechnology.com/cafe/stats")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
