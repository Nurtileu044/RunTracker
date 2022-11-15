package kz.ablazim.runtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    signUpScreenContent: @Composable () -> Unit,
    signInScreenContent: @Composable () -> Unit,
    homeScreenContent: @Composable () -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(route = Screen.SignIn.route) {
            signInScreenContent()
        }
        composable(route = Screen.SignUp.route) {
            signUpScreenContent()
        }
        composable(route = Screen.Home.route) {
            homeScreenContent()
        }
    }
}