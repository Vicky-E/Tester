package com.vicks.tester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicks.tester.ui.theme.Purple
import com.vicks.tester.ui.theme.PurpleGrey40
import com.vicks.tester.ui.theme.TesterTheme
import com.vicks.tester.ui.theme.dark
import com.vicks.tester.ui.theme.light
import com.vicks.tester.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appViewModel = AppViewModel(this.application)
        setContent {
            TesterTheme {
                Guesser(appViewModel = appViewModel)
            }
        }
    }
}
@Composable
fun Onboard() {
    val headings = Font(R.font.frank_medium)
    // val body = Font(R.font.FraRegular)
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp, 20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.pic),
            contentDescription = "picture of rocket launching",
            modifier = Modifier.size(388.dp, 400.dp),
            alignment = Alignment.Center
        )
        Column() {
            Text(
                text = "Go Beyond Your Imagination",
                fontFamily = FontFamily(Font(R.font.frank_medium))
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Guesser(appViewModel: AppViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(20.dp, 20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isStart = remember{ mutableStateOf(false) }
            //   Splash()


        if(!appViewModel.gameState.isStart.value) {
            Splash()
            Button(
                onClick = {
                    appViewModel.startGame()
                    isStart.value = true
                },
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .size(188.dp, 48.dp)
                    .clip(RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = light,
                    disabledContentColor = DarkGray,
                    disabledContainerColor = Gray
                ),
            ) {
                Text(
                    "Start Game",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.frank_medium))
                )
            }
        }

else {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Have Fun Playing")
        OutlinedTextField(
            value = appViewModel.guessString.value,
            onValueChange = { appViewModel.guessString.value = it },
            placeholder = { Text("Enter guess") },
            label = { Text("Guess", textAlign = TextAlign.Start) },
            modifier = Modifier
                .size(388.dp, 60.dp)
                .clip(RoundedCornerShape(6.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = dark,
                disabledTextColor = Gray,
                focusedBorderColor = PurpleGrey40
            )
        )

        Text(
            text = appViewModel.gameState.message.value,
            style = TextStyle(color = if (appViewModel.gameState.isCorrect.value) Color.Green else Color.Red),
            fontSize = 16.sp
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp),modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { appViewModel.check() },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(140.dp, 48.dp)
                    .clip(RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = light,
                    disabledContentColor = DarkGray,
                    disabledContainerColor = Gray
                )
            ) {
                Text("Check", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.frank_medium)))
            }
            Button(
                onClick = {
                    appViewModel.endGame()
                    isStart.value = false
                          },
                modifier = Modifier
                    .padding(vertical =  8.dp)
                    .size(140.dp, 48.dp)
                    .clip(RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = light,
                    disabledContentColor = DarkGray,
                    disabledContainerColor = Gray
                )
            ) {
                Text(
                    "End Game",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.frank_medium))
                )
            }
        }


    }
}
        }




}
@Composable
fun Splash() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Guesser",
            fontSize = 24.sp,
            color = Purple,
            fontFamily = FontFamily(Font(R.font.frank_medium)),
            textAlign = TextAlign.Center
        )
        Text(
            text = "click 'Start Game' to begin",
            fontSize = 16.sp,
            color = dark,
            fontFamily = FontFamily(Font(R.font.frankruhllibre_regular))
        )
    }
}