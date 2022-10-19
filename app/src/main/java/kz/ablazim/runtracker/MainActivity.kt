package kz.ablazim.runtracker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kz.ablazim.runtracker.auth.presentation.compose.AuthScreen
import kz.ablazim.runtracker.auth.presentation.compose.AuthViewModel

private lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            AuthScreen(authViewModel = AuthViewModel(), onNavToSignUpPage = { /*TODO*/ }) {

            }
        }
    }
}