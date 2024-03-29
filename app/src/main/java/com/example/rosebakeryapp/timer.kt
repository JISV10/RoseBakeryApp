package com.example.rosebakeryapp
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme

class TimerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoseBakeryAppTheme {
                // A surface container using the 'background' color from the theme
                TimerScreen()
            }
        }
    }
}


@Composable
fun TimerScreen() {
    var durationInput by remember { mutableStateOf("") }
    var timeLeft by remember { mutableLongStateOf(0L) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var totalDuration by remember { mutableLongStateOf(0L) }

    val timerState = remember(totalDuration) {
        object : CountDownTimer(totalDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
            }

            override fun onFinish() {
                isTimerRunning = false
            }
        }
    }

    DisposableEffect(key1 = timerState) {
        onDispose {
            timerState.cancel()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Timer",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = durationInput,
                onValueChange = { durationInput = it },
                label = { Text("Enter duration in minutes") },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    if (isTimerRunning) {
                        // Stop the timer
                        timerState.cancel()
                        isTimerRunning = false
                    } else {
                        // Start the timer
                        val duration = durationInput.toLongOrNull() ?: 0
                        totalDuration = duration * 60000L
                        timeLeft = totalDuration
                        isTimerRunning = true
                        timerState.start()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF9968D),
                    contentColor = Color.White
                )
            ) {
                Text(if (isTimerRunning) "Stop" else "Start")
            }

            val progress = remember(timeLeft, totalDuration) {
                if (totalDuration > 0) {
                    timeLeft.toFloat() / totalDuration.toFloat()
                } else {
                    0f
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 16.dp)
            ) {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF9968D),
                    strokeWidth = 16.dp
                )

                val minutes = (timeLeft / 1000) / 60
                val seconds = (timeLeft / 1000) % 60
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)

                Text(
                    text = timeLeftFormatted,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    RoseBakeryAppTheme {
        TimerScreen()
    }
}