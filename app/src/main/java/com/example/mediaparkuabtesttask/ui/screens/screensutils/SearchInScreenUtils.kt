package com.example.mediaparkuabtesttask.ui.screens.screensutils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mediaparkuabtesttask.ui.theme.lightGray

object SearchInScreenUtils {

    @Composable
    fun SearchInChooser(switcherText:String, content: @Composable () -> Unit ) {
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = switcherText, style = MaterialTheme.typography.body2)
            content.invoke()
        }
        Spacer(Modifier.height(10.dp))
        Box(Modifier.height(2.dp).background(lightGray).fillMaxWidth())
    }
}