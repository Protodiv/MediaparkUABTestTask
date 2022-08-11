package com.example.mediaparkuabtesttask.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.mediaparkuabtesttask.MainViewModel
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils.HeaderText
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils.HeaderWithLogo
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils.NewsBox
import com.example.mediaparkuabtesttask.ui.theme.gray
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun NewsScreen(
    topic: String = "News",
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val newsData = viewModel.news
    val stateScroll = rememberScrollState()

    Column(
        modifier = Modifier
            .background(gray)
            .fillMaxSize()
    ) {
        HeaderWithLogo()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .zIndex(0f)
                .verticalScroll(stateScroll)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            HeaderText(topic, Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.height(15.dp))

            newsData.articles.forEach { article ->
                NewsBox(article) { articleUrl ->
                    val encodedUrl = URLEncoder.encode(articleUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate(
                        Screen.WebViewScreen.route + "/$encodedUrl"
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Spacer(modifier = Modifier.height(75.dp))
        }
    }
}