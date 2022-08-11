package com.example.mediaparkuabtesttask.ui.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils.ErrorWindow

@Composable
fun WebViewScreen(url:String?, navController: NavController) {
    if(url == null){
        ErrorWindow(
            "Wrong Url"
        ){
            navController.navigateUp()
        }
    }else{
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
    }
}