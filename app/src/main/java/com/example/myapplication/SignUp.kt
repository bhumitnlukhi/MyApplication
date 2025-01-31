package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SignUpScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var userNameError by remember { mutableStateOf(false) }
    var confPasswordError by remember { mutableStateOf(false) }
    var tAndC by remember { mutableStateOf( false) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .hideKeyboardOnOutsideClick()
        ) {
            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp),
                horizontalAlignment = Alignment.Start) {

                Column(modifier = Modifier.fillMaxHeight(
                    fraction = 0.93f
                ).verticalScroll(rememberScrollState())) {
                    Spacer(modifier = Modifier.height(15.dp))

                    //Back button
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Spacer(modifier = Modifier.height(26.dp))

                    CommonText(
                        textValue = "Sign Up", textStyle = TextStyle(
                            color = colorResource(id = R.color.purple7063BF),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Divider(color = colorResource(id = R.color.purple7063BF), thickness = 3.dp,modifier = Modifier.weight(1f))
                        Divider(color = colorResource(id = R.color.clr5B5B5B), thickness = 1.dp,modifier = Modifier.weight(2f))
                    }
                    Spacer(modifier = Modifier.height(36.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_person),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "Name of Recommender *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(26.dp))

                    // Mobile Number Input Field
                    CommonInputTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                            userNameError = it.isEmpty()
                        },
                        label = "User Name",
                        placeholder = "Bhumit Lukhi",
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        isError = userNameError,
                        errorMessage = "User name is required"
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_phone),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "Mobile Number *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(26.dp))

                    // Mobile Number Input Field
                    CommonInputTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            phoneError = it.isEmpty()
                        },
                        label = "Mobile Number",
                        placeholder = "+918140557546",
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        isError = phoneError,
                        errorMessage = "Mobile number is required"
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_lock),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "New Password *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Input Field
                    CommonInputTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = it.isEmpty()
                        },
                        label = "Password",
                        isPassword = true,
                        isError = passwordError,
                        errorMessage = "Password is required"
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_lock),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "Confirm Password *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Input Field
                    CommonInputTextField(
                        value = confPassword,
                        onValueChange = {
                            confPassword = it
                            confPasswordError = it.isEmpty()
                        },
                        label = "Confirm Password",
                        isPassword = true,
                        isError = confPasswordError,
                        errorMessage = "Confirm Password is required"
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { tAndC = !tAndC }) {
                            Image(
                                painter = if (tAndC) painterResource(id = R.drawable.svg_selected_check) else painterResource(id = R.drawable.svg_unselected),
                                contentDescription = null
                            )
                        }
                        TermsAndConditionText()
                    }



                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp) // Added padding for better spacing
                            .height(40.dp), // Added height to ensure visibility
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.weight(1f) // Ensures the divider takes available space
                        )

                        Spacer(modifier = Modifier.width(8.dp)) // Increased spacing for better UI

                        CommonText(
                            textValue = "or continue with",
                            textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.wrapContentWidth() // Ensure text doesn't get squished
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(35.dp))

                    // Social Login Buttons
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        SocialIcon(painterResource(id = R.drawable.svg_google), "Google")
                        SocialIcon(painterResource(id = R.drawable.svg_twitter), "Twitter")
                        SocialIcon(painterResource(id = R.drawable.svg_apple), "Apple")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Box(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        // AnnotatedClickableText(navController)
                    }
                    Spacer(modifier = Modifier.height(40.dp))

                }
                //send otp button
                Box(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    RoundedButton(text = "Send OTP") {
                        if(phoneNumber.isNotEmpty() && password.isNotEmpty()){
                            // navController.popBackStack()
                            navController.navigate("otp_screen/{mobileNumber}".replace(
                                oldValue = "{mobileNumber}",
                                newValue = "+91$phoneNumber"
                            ))
                        }
                        else if(phoneNumber.isEmpty() && password.isEmpty()){
                            phoneError = true
                            passwordError = true

                        }
                        else if(phoneNumber.isEmpty()){
                            phoneError = true
                        }
                        else {
                            passwordError = true
                        }
                    }
                }








            }
        }



    }

}

@Preview
@Composable
fun SignUpTest() {
    var phoneNumber by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var userNameError by remember { mutableStateOf(false) }
    var confPasswordError by remember { mutableStateOf(false) }
    var tAndC by remember { mutableStateOf( false) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .hideKeyboardOnOutsideClick()
        ) {
            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp),
                horizontalAlignment = Alignment.Start) {
                Column(
                    modifier = Modifier.fillMaxHeight(
                        fraction = 0.93f
                    ).verticalScroll(rememberScrollState())
                ) {

                    Spacer(modifier = Modifier.height(15.dp))

                    //Back button
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(26.dp))

                    CommonText(
                        textValue = "Sign Up", textStyle = TextStyle(
                            color = colorResource(id = R.color.purple7063BF),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Divider(color = colorResource(id = R.color.purple7063BF), thickness = 3.dp,modifier = Modifier.weight(1f))
                        Divider(color = colorResource(id = R.color.clr5B5B5B), thickness = 1.dp,modifier = Modifier.weight(2f))
                    }
                    Spacer(modifier = Modifier.height(36.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_person),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "Name of Recommender *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(26.dp))

                    // Mobile Number Input Field
                    CommonInputTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                            userNameError = it.isEmpty()
                        },
                        label = "User Name",
                        placeholder = "Bhumit Lukhi",
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        isError = userNameError,
                        errorMessage = "User name is required"
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_phone),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "Mobile Number *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(26.dp))

                    // Mobile Number Input Field
                    CommonInputTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            phoneError = it.isEmpty()
                        },
                        label = "Mobile Number",
                        placeholder = "+918140557546",
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        isError = phoneError,
                        errorMessage = "Mobile number is required"
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_lock),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "New Password *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Input Field
                    CommonInputTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = it.isEmpty()
                        },
                        label = "Password",
                        isPassword = true,
                        isError = passwordError,
                        errorMessage = "Password is required"
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.svg_lock),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        CommonText(
                            textValue = "Confirm Password *", textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Input Field
                    CommonInputTextField(
                        value = confPassword,
                        onValueChange = {
                            confPassword = it
                            confPasswordError = it.isEmpty()
                        },
                        label = "Confirm Password",
                        isPassword = true,
                        isError = confPasswordError,
                        errorMessage = "Confirm Password is required"
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { tAndC = !tAndC }) {
                            Image(
                                painter = if (tAndC) painterResource(id = R.drawable.svg_selected_check) else painterResource(id = R.drawable.svg_unselected),
                                contentDescription = null
                            )
                        }
                        TermsAndConditionText()
                    }



                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp) // Added padding for better spacing
                            .height(40.dp), // Added height to ensure visibility
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.weight(1f) // Ensures the divider takes available space
                        )

                        Spacer(modifier = Modifier.width(8.dp)) // Increased spacing for better UI

                        CommonText(
                            textValue = "or continue with",
                            textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.wrapContentWidth() // Ensure text doesn't get squished
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(35.dp))

                    // Social Login Buttons
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        SocialIcon(painterResource(id = R.drawable.svg_google), "Google")
                        SocialIcon(painterResource(id = R.drawable.svg_twitter), "Twitter")
                        SocialIcon(painterResource(id = R.drawable.svg_apple), "Apple")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Box(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        // AnnotatedClickableText(navController)
                    }
                    Spacer(modifier = Modifier.height(40.dp))

                }
                //login button
                Box(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    RoundedButton(text = "sign in") {
                        if(phoneNumber.isNotEmpty() && password.isNotEmpty()){
                           // navController.popBackStack()
                        }
                        else if(phoneNumber.isEmpty() && password.isEmpty()){
                            phoneError = true
                            passwordError = true

                        }
                        else if(phoneNumber.isEmpty()){
                            phoneError = true
                        }
                        else {
                            passwordError = true
                        }
                    }
                }








            }
        }



    }
}

@Composable
fun TermsAndConditionText() {
    val annotatedText = buildAnnotatedString {
        //append your initial text
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.clr5B5B5B),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("Accept ")

        }

        //Start of the pushing annotation which you want to color and make them clickable later
        pushStringAnnotation(
            tag = "TandC",// provide tag which will then be provided when you click the text
            annotation = "TandC"
        )
        //add text with your different color/style
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.purple7063BF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Terms & Condition")
        }
        // when pop is called it means the end of annotation with current tag
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "TandC",// tag which you used in the buildAnnotatedString
                start = offset,
                end = offset
            )[0].let {
                //navController.navigate("signUp_screen")
            }
        }
    )
}





