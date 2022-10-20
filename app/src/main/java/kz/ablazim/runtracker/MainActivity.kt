package kz.ablazim.runtracker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.ablazim.runtracker.auth.presentation.compose.AuthScreen
import kz.ablazim.runtracker.auth.presentation.compose.AuthViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
            AuthScreen(authViewModel = authViewModel, onNavToSignUpPage = { /*TODO*/ }) {

            }
        }
    }
}