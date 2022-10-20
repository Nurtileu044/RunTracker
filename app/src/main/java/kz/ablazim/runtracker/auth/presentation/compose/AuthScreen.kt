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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import kz.ablazim.runtracker.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel? = null,
    onNavToSignUpPage: () -> Unit,
    onNavToHomePage: () -> Unit
) {
    val authUiState = authViewModel?.authUiState
    val isError = authUiState?.loginError != null
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
                text = stringResource(id = R.string.log_in),
                style = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authUiState?.userName ?: "",
                onValueChange = { authViewModel?.onUserNameChange(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                },
                label = {
                    Text(text = "Email")
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
                value = authUiState?.password ?: "",
                onValueChange = { authViewModel?.onPasswordChange(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                isError = isError
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = { authViewModel?.loginUser(context) }) {
                Text(text = stringResource(id = R.string.log_in), style = TextStyle(fontSize = 20.sp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = stringResource(id = R.string.can_not_remember_your_password),
                    style = TextStyle(fontSize = 14.sp, color = Color.Black, textAlign = TextAlign.End)
                )
                TextButton(modifier = Modifier.weight(1f), onClick = {}) {
                    Text(
                        text = stringResource(id = R.string.reset_password),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.End
                        )
                    )
                }
            }
            if (isError) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = authUiState?.loginError ?: "unknown error",
                    style = TextStyle(fontSize = 14.sp, color = Color.Red, textAlign = TextAlign.End)
                )
            }

            LaunchedEffect(key1 = authViewModel?.hasUser) {
                if (authViewModel?.hasUser == true) {
                    onNavToHomePage()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(onNavToSignUpPage = {}) {

    }
}