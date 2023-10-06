package com.appringer.myapplication.utils

sealed class NavigationScreen(val route:String)
{
    object SplashScreen : NavigationScreen("splashScreen")
    object HomeScreen : NavigationScreen("homeScreen")
    object CreateNotesScreen : NavigationScreen("createNoteScreen")
}
