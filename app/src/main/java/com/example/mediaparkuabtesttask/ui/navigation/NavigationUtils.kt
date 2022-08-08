package com.example.mediaparkuabtesttask.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mediaparkuabtesttask.ui.screens.Screen

object NavigationUtils{
    val selectedColor = Color(0xffF68F54)
    val unSelectedColor = Color(0xff939DAE)

    val bottomNavItems:List<BottomNavItem> = Screen.values().mapNotNull { it.getBottomNavItem() }

    @Composable
    fun BottomNavigationIcon(bottomNavItem: BottomNavItem){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = bottomNavItem.icon),
                contentDescription = bottomNavItem.name
            )
            Text(
                text = bottomNavItem.name,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 14.sp
            )
        }
    }
}