package com.example.mediaparkuabtesttask.ui.navigation

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mediaparkuabtesttask.MainViewModel
import com.example.mediaparkuabtesttask.ui.navigation.NavigationUtils.bottomNavItems
import com.example.mediaparkuabtesttask.ui.screens.*

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (
                currentRoute != Screen.FilterScreen.route &&
                currentRoute != Screen.SearchInScreen.route
            ) {
                BottomNavigationBar(
                    items = bottomNavItems,
                    navController = navController,
                    onItemClick = { bottomNavItem ->
                        navController.navigate(bottomNavItem.rout)
                    },
                    modifier = Modifier.shadow(12.dp)
                )
            }
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = Screen.NewsScreen.route
        ) {
            composable(
                route = Screen.NewsScreen.route,
            ) {
                NewsScreen("News", viewModel, navController)
            }
            composable(route = Screen.HomeScreen.route) {

            }
            composable(
                route = Screen.SearchScreen.route,
            ) {
                viewModel.getSearchHistory()
                SearchScreen(navController, viewModel)
            }
            composable(route = Screen.ProfileScreen.route) {

            }
            composable(route = Screen.MoreScreen.route) {

            }
            composable(
                route = Screen.FilterScreen.route,
            ) {
                FilterScreen(navController, viewModel)
            }
            composable(
                route = Screen.SearchInScreen.route,
            ) {
                SearchInScreen(navController, viewModel)
            }
            composable(
                route = Screen.WebViewScreen.route + "/{url}",
                arguments = listOf(
                    navArgument("url") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                WebViewScreen(url = backStackEntry.arguments?.getString("url"), navController)
            }
        }


    }
}