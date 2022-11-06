package kz.ablazim.runtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.ablazim.runtracker.auth.presentation.compose.AuthScreen
import kz.ablazim.runtracker.auth.presentation.compose.AuthViewModel
import kz.ablazim.runtracker.auth.presentation.compose.HomeScreen
import kz.ablazim.runtracker.auth.presentation.compose.SignUpPage

enum class LoginRoutes {
    SignUp,
    SignIn
}

enum class HomeRoutes {
    Home,
    Details
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = LoginRoutes.SignIn.name) {
        composable(route = LoginRoutes.SignIn.name) {
            AuthScreen(authViewModel = authViewModel, onNavToSignUpPage = {
                navController.navigate(LoginRoutes.SignUp.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }) {
                navController.navigate(HomeRoutes.Home.name) {
                    launchSingleTop = true
                    popUpTo(route = LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = LoginRoutes.SignUp.name) {
            SignUpPage(authViewModel = authViewModel, onNavToLoginPage = {
                navController.navigate(LoginRoutes.SignIn.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }) {
                navController.navigate(HomeRoutes.Home.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignUp.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = HomeRoutes.Home.name) {
            HomeScreen()
        }
    }
}