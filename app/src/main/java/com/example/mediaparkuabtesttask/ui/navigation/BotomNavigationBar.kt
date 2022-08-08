package com.example.mediaparkuabtesttask.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mediaparkuabtesttask.ui.navigation.NavigationUtils.BottomNavigationIcon
import com.example.mediaparkuabtesttask.ui.navigation.NavigationUtils.selectedColor
import com.example.mediaparkuabtesttask.ui.navigation.NavigationUtils.unSelectedColor
import com.example.mediaparkuabtesttask.ui.theme.white

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick:(BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = white,
        elevation = 5.dp
    ) {

        items.forEach { item ->
            val selected = item.rout == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = selectedColor,
                unselectedContentColor = unSelectedColor,
                icon = {
                    BottomNavigationIcon(item)
                }
            )
        }
    }
}