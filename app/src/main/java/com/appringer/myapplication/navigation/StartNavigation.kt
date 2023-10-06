package com.appringer.myapplication.navigation

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appringer.myapplication.screen.CreateNotesActivity
import com.appringer.myapplication.screen.HomeScreenUI
import com.appringer.myapplication.screen.SplashScreen
import com.appringer.myapplication.utils.NavigationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartNavigation(context: Context) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreen.SplashScreen.route) {
        composable(NavigationScreen.SplashScreen.route)
        {
            SplashScreen(navController)
        }

        composable(NavigationScreen.HomeScreen.route)
        {
            HomeScreenUI(context,navController)
        }
        composable(NavigationScreen.CreateNotesScreen.route)
        {
            CreateNotesActivity(context,navController)
        }

    }

}

