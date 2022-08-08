package com.example.mediaparkuabtesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaparkuabtesttask.ui.navigation.Navigation
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils
import com.example.mediaparkuabtesttask.ui.theme.Shapes
import com.example.mediaparkuabtesttask.ui.theme.Typography
import com.example.mediaparkuabtesttask.ui.theme.white
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:MainViewModel by viewModels()
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colors = colors,
                typography = Typography,
                shapes = Shapes
            ){
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = white
                    )
                }
                Navigation(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}