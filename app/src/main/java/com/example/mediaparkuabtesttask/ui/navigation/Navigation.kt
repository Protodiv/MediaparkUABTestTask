package com.example.mediaparkuabtesttask.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mediaparkuabtesttask.MainViewModel
import com.example.mediaparkuabtesttask.ui.navigation.AnimationUtils.enterTransition
import com.example.mediaparkuabtesttask.ui.navigation.AnimationUtils.exitTransition
import com.example.mediaparkuabtesttask.ui.navigation.AnimationUtils.popEnterTransition
import com.example.mediaparkuabtesttask.ui.navigation.AnimationUtils.popExitTransition
import com.example.mediaparkuabtesttask.ui.navigation.NavigationUtils.bottomNavItems
import com.example.mediaparkuabtesttask.ui.screens.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
//    val navController = rememberAnimatedNavController()
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

//        AnimatedNavHost(
        NavHost(
            navController = navController,
            startDestination = Screen.NewsScreen.route
        ) {
            composable(
                route = Screen.NewsScreen.route,
//                exitTransition = exitTransition,
//                popEnterTransition = popEnterTransition,
//                enterTransition = enterTransition,
//                popExitTransition = popExitTransition
            ) {
                NewsScreen("News", viewModel)
            }
            composable(route = Screen.HomeScreen.route) {

            }
            composable(
                route = Screen.SearchScreen.route,
//                exitTransition = exitTransition,
//                popEnterTransition = popEnterTransition,
//                enterTransition = enterTransition,
//                popExitTransition = popExitTransition
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
//                exitTransition = exitTransition,
//                popEnterTransition = popEnterTransition,
//                enterTransition = enterTransition,
//                popExitTransition = popExitTransition
            ) {
                FilterScreen(navController, viewModel)
            }
            composable(
                route = Screen.SearchInScreen.route,
//                exitTransition = exitTransition,
//                popEnterTransition = popEnterTransition,
//                enterTransition = enterTransition,
//                popExitTransition = popExitTransition
            ) {
                SearchInScreen(navController, viewModel)
            }
        }


    }
}