package com.example.mediaparkuabtesttask.ui.screens

import com.example.mediaparkuabtesttask.R
import com.example.mediaparkuabtesttask.ui.navigation.BottomNavItem

enum class Screen(
    val route: String,
    val title: String,
    val icon: Int? = null,
) {

    HomeScreen("home_screen", "Home", R.drawable.ic_home),
    NewsScreen("news_screen", "News", R.drawable.ic_news),
    SearchScreen("search_screen", "Search",R.drawable.ic_search),
    ProfileScreen("profile_screen", "Profile", R.drawable.ic_profile),
    MoreScreen("more_screen", "More", R.drawable.ic_profile),

    FilterScreen("filter_screen", "Filter"),
    SearchInScreen("searchIn_screen", "SearchIn");

    fun getBottomNavItem(): BottomNavItem? {
        if(icon == null){
           return null
        }
        return BottomNavItem(
            title,
            route,
            icon
        )
    }
}
