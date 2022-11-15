package kz.ablazim.runtracker.auth.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kz.ablazim.runtracker.R
import kz.ablazim.runtracker.navigation.Screen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpPage(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel? = null,
    navController: NavController
) {
    val authUiState = authViewModel?.authUiState
    val isLoading = authUiState?.isLoading
    val isError = authUiState?.signUpError != null
    val isSuccessfulLogin = authUiState?.isSuccessfulLogin
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .width(IntrinsicSize.Min),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                style = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authUiState?.userNameSignUp ?: "",
                onValueChange = { authViewModel?.onUserNameChangeSignUp(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                isError = isError
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authUiState?.passwordSignUp ?: "",
                onValueChange = { authViewModel?.onPasswordChangeSignUp(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                isError = isError
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authUiState?.confirmPasswordSignUp ?: "",
                onValueChange = { authViewModel?.onConfirmPasswordChange(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.confirm_password))
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                isError = isError
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = { authViewModel?.createUser(context) }) {
                Text(text = stringResource(id = R.string.sign_up), style = TextStyle(fontSize = 20.sp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = stringResource(id = R.string.already_have_an_account),
                    style = TextStyle(fontSize = 14.sp, color = Color.Black, textAlign = TextAlign.End)
                )
                TextButton(modifier = Modifier.weight(1f), onClick = { navController.navigate(Screen.SignIn.route) }) {
                    Text(
                        text = stringResource(id = R.string.sign_in),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.End
                        )
                    )
                }
            }

            if (isLoading == true) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary, strokeWidth = 2.dp)
            }

            if (isSuccessfulLogin == true) {
                navController.navigate(Screen.Home.route)
            }

            if (isError) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = authUiState?.signUpError ?: "unknown error",
                    style = TextStyle(fontSize = 14.sp, color = Color.Red, textAlign = TextAlign.End)
                )
            }

            LaunchedEffect(key1 = authViewModel?.hasUser) {
                if (authViewModel?.hasUser == true) {
                    navController.navigate(Screen.Home.route)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpPreview() {
    SignUpPage(navController = rememberNavController())
}