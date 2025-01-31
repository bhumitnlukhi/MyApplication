package com.example.myapplication

import OtpInputField
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpScreen(navController: NavController,phoneNumber: String) {
    var seconds by remember { mutableIntStateOf(30) }

    val context = LocalContext.current
    var otpValue by remember { mutableStateOf("") }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current



    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
        keyboardController?.show()
        // Customize the delay time
        while (true){
            delay(1.seconds)
            if(seconds > 0 ){
                seconds -= 1
            }
        }


    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .hideKeyboardOnOutsideClick()
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier.align(alignment = Alignment.Start)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.svg_cross),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                        }
                    )
                }
                //Back button

                Spacer(modifier = Modifier.height(16.dp))

                CommonText(
                    textValue = "OTP Verify", textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                CommonText(
                    textValue = "OTP has sent to your mobile number", textStyle = TextStyle(
                        color = colorResource(id = R.color.clr5B5B5B),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(25.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    CommonText(
                        textValue = phoneNumber, textStyle = TextStyle(
                            color = colorResource(id = R.color.purple1D0330),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.svg_edit),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                        }
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))
                OtpInputField(
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .focusRequester(focusRequester),
                    otpText = otpValue,
                    shouldCursorBlink = false,
                    onOtpModified = { value, otpFilled ->
                        otpValue = value
                        isOtpFilled = otpFilled
                        if (otpFilled) {
                            keyboardController?.hide()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(25.dp))

                CommonText(
                    textValue = "Resend Code In", textStyle = TextStyle(
                        color = colorResource(id = R.color.clr5B5B5B),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(25.dp))

                CommonText(
                    textValue = "00:${if(seconds < 10) {"0"} else { "" }
                    }$seconds", textStyle = TextStyle(
                        color = colorResource(id = R.color.clr5B5B5B),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(25.dp))

                Row {
                    CommonText(
                        textValue = "Don't receive code? ", textStyle = TextStyle(
                            color = colorResource(id = R.color.clr5B5B5B),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    CommonText(
                        textValue = "RESEND", textStyle = TextStyle(
                            color = colorResource(id = R.color.purple7063BF),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline

                        ),
                        modifier = Modifier.clickable {
                            if(seconds == 0){
                                seconds = 30
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                //send otp button
                Box(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    RoundedButton(text = "Verify") {
                    }
                }


            }
        }
    }
}




