@file:OptIn(ExperimentalMaterial3Api::class)

package com.akashnavik.bmicalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akashnavik.bmicalculator.ui.theme.BMICalculatorTheme
import kotlinx.coroutines.newSingleThreadContext
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                BMIAPP()
            }
        }
    }
}

@Composable
fun BMIAPP() {

    var Age by remember { mutableStateOf("") }
    var Height by remember { mutableStateOf("") }
    var Weight by remember { mutableStateOf("") }
    var alert by remember { mutableStateOf(Color.White) }
    var result by remember { mutableStateOf("") }
    var bmiTxt by remember {
        mutableStateOf("")
    }
    var resultCard by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        , horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Column(modifier = Modifier.padding(top = 50.dp), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "BMI Calculator", fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold, color = Color.White
            )

            Spacer(modifier = Modifier.padding(16.dp))

            TextField(
                value = Age, onValueChange = { Age = it },
                label = { Text(text = "Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    containerColor = Color(0x9D141414),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = Height, onValueChange = { Height = it },
                label = { Text(text = "Height (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    containerColor = Color(0x9D141414),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = Weight, onValueChange = { Weight = it },
                label = { Text(text = "Weight (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    containerColor = Color(0x9D141414),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        var meter = Height.toFloat() / 100
                        var bmi = Weight.toFloat() / (meter * meter)
                        bmiTxt = bmi.toString()

                        when {
                            bmi > 18.5 -> {

                                result = "UnderWeight"
                                alert = Color(255, 225, 137)
                                resultCard =true
                            }

                            bmi >= 18.5 && bmi <= 24.9 -> {
                                resultCard =true
                                result = "Normal"
                                alert = Color(140, 212, 126)
                            }

                            bmi >= 25.0 && bmi <= 39.9 -> {
                                resultCard =true
                                result = "Overweight"
                                alert = Color(255, 181, 76)
                            }

                            bmi >= 40.0 -> {
                                resultCard =true
                                result = "Obese"
                                alert = Color(255, 105, 98)
                            }
                        }


                    },
                    shape = RectangleShape, modifier = Modifier
                        .size(width = 280.dp, height = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.White
                    )
                )
                {
                    Text(text = "Calculate BMI", color = Color.Black,)
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            if (resultCard) {
                Card(

                    modifier = Modifier
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(alert),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Your BMI is: ${bmiTxt}", style = MaterialTheme.typography.labelLarge)
                        Text(
                            "BMI Interpretation: ${result}",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}



