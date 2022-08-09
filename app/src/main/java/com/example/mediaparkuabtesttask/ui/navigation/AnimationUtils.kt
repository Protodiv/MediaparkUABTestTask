package com.example.mediaparkuabtesttask.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@ExperimentalAnimationApi
object AnimationUtils {
    const val initialDuration = 300
    const val initialOffset = 300
    val exitTransition: (AnimatedContentScope<String>.(NavBackStackEntry, NavBackStackEntry) -> ExitTransition?) = { _,_ ->
        slideOutHorizontally(
            targetOffsetX = {-initialOffset},
            animationSpec = tween(initialDuration)
        ) + fadeOut(animationSpec = tween(initialDuration))
    }
    val enterTransition:(AnimatedContentScope<String>.(NavBackStackEntry, NavBackStackEntry) -> EnterTransition?) = { _,_ ->
        slideInHorizontally(
            initialOffsetX = {-initialOffset},
            animationSpec = tween(initialDuration)
        ) + fadeIn(animationSpec = tween(initialDuration))
    }


    val popEnterTransition: (AnimatedContentScope<String>.(NavBackStackEntry, NavBackStackEntry) -> EnterTransition?) = { _,_ ->
        slideInHorizontally(
            initialOffsetX = {-initialOffset},
            animationSpec = tween(initialDuration)
        ) + fadeIn(animationSpec = tween(initialDuration))
    }
    val popExitTransition:(AnimatedContentScope<String>.(NavBackStackEntry, NavBackStackEntry) -> ExitTransition?) = {_,_ ->
        slideOutHorizontally(
            targetOffsetX = {-initialOffset},
            animationSpec = tween(initialDuration)
        ) + fadeOut(animationSpec = tween(initialDuration))
    }
}