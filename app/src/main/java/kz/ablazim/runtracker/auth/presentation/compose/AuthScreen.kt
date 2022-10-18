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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.ablazim.runtracker.R

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
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
            OutlinedTextField(value = username, onValueChange = { username = it })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = password, onValueChange = { password = it })
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.log_in), style = TextStyle(fontSize = 20.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.can_not_remember_your_password),
                    style = TextStyle(fontSize = 14.sp, color = Color.Black, textAlign = TextAlign.End)
                )
                Text(
                    modifier = Modifier.weight(0f),
                    text = stringResource(id = R.string.reset_password),
                    style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colors.primary, textAlign = TextAlign.End)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}