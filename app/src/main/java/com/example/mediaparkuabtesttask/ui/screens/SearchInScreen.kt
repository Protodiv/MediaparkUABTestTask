package com.example.mediaparkuabtesttask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mediaparkuabtesttask.MainViewModel
import com.example.mediaparkuabtesttask.R
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils
import com.example.mediaparkuabtesttask.ui.screens.screensutils.SearchInScreenUtils.SearchInChooser
import com.example.mediaparkuabtesttask.ui.theme.lightGray
import com.example.mediaparkuabtesttask.ui.theme.orange
import com.example.mediaparkuabtesttask.ui.theme.white

@Composable
fun SearchInScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {

    var isTitleUsed by remember { mutableStateOf(true)}
    var isDescriptionUsed by remember { mutableStateOf(true)}
    var isContentUsed by remember { mutableStateOf(true)}

    val switchColors = SwitchDefaults.colors(
        checkedThumbColor = white,
        checkedTrackColor = orange,
        checkedTrackAlpha = 1f,
        uncheckedThumbColor = white,
        uncheckedTrackColor = lightGray,
        uncheckedTrackAlpha = 1f,
        disabledCheckedThumbColor = white,
        disabledCheckedTrackColor = orange,
        disabledUncheckedThumbColor = white,
        disabledUncheckedTrackColor = orange,
    )

    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
    ) {
        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(86.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = orange,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.FilterScreen.route)
                    }
                )
                Button(
                    onClick = {
                        viewModel.clearFilterSearchIn()
                        isTitleUsed = true
                        isDescriptionUsed = true
                        isContentUsed = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = orange,
                        disabledBackgroundColor = Color.Transparent,
                        disabledContentColor = orange,
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("Clear")
                    Spacer(Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = null
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            ScreensUtils.HeaderText(text = "Search in")
            Spacer(Modifier.height(20.dp))

            SearchInChooser("Title"){
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Switch(
                        checked = isTitleUsed,
                        enabled = true,
                        onCheckedChange = { isTitleUsed = it },
                        colors = switchColors,
                        modifier = Modifier
                            .background(if(isTitleUsed) orange else lightGray)
                            .clip(CircleShape)
                    )
                }
            }

            SearchInChooser("Description"){
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Switch(
                        checked = isDescriptionUsed,
                        onCheckedChange = { isDescriptionUsed = it },
                        colors = switchColors,
                        modifier = Modifier
                            .background(if(isDescriptionUsed) orange else lightGray)
                            .clip(CircleShape)
                    )
                }
            }

            SearchInChooser("Content"){
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Switch(
                        checked = isContentUsed,
                        onCheckedChange = { isContentUsed = it },
                        modifier = Modifier
                            .background(if(isContentUsed) orange else lightGray)
                            .clip(CircleShape),
                        colors = switchColors
                    )
                }
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)){
                Button(
                    onClick = {
                        navController.navigate(Screen.FilterScreen.route)
                        viewModel.setNewSearchIn(
                            isTitleUsed,
                            isDescriptionUsed,
                            isContentUsed
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = orange,
                        contentColor = white,
                        disabledBackgroundColor = orange,
                        disabledContentColor = white,
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 1.dp,
                        disabledElevation = 0.dp
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                ) {
                    Text("Apply")
                }
            }
        }
    }
}