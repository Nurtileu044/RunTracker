package kz.ablazim.runtracker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import kz.ablazim.runtracker.auth.presentation.compose.AuthScreen
import kz.ablazim.runtracker.auth.presentation.compose.AuthViewModel
import kz.ablazim.runtracker.auth.presentation.compose.HomeScreen
import kz.ablazim.runtracker.auth.presentation.compose.SignUpPage
import kz.ablazim.runtracker.navigation.AppNavGraph

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
            val navController = rememberNavController()
            AppNavGraph(
                navController = navController,
                signUpScreenContent = { SignUpPage(authViewModel = authViewModel, navController = navController) },
                signInScreenContent = { AuthScreen(authViewModel = authViewModel, navController = navController) },
                homeScreenContent = { HomeScreen() })
        }
    }
}