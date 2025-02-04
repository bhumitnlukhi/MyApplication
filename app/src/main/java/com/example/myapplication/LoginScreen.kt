package com.example.myapplication

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.api_service.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController,viewModel: LoginViewModel = hiltViewModel()) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val loginResponse = viewModel.loginResponse.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value


    LaunchedEffect(Unit) {
        viewModel.gettingUserName()
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .hideKeyboardOnOutsideClick()
        ) {
            if (isLoading) {
                Box(  modifier = Modifier.align(alignment = Alignment.Center)
                ) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {

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
                        textValue = "Sign in", textStyle = TextStyle(
                            color = colorResource(id = R.color.purple7063BF),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Divider(color = colorResource(id = R.color.purple7063BF), thickness = 3.dp)

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
                            textValue = "Password *", textStyle = TextStyle(
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
                    Spacer(modifier = Modifier.height(10.dp))

                    //forgot password
                    Box(
                        modifier = Modifier.align(alignment = Alignment.End)
                    ) {
                        CommonText(
                            textValue = "Forgot password?",
                            textStyle = TextStyle(
                                color = colorResource(id = R.color.purple1D0330),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.clickable {

                            }

                        )
                    }

                    Spacer(modifier = Modifier.height(45.dp))

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
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SocialIcon(painterResource(id = R.drawable.svg_google), "Google")
                        SocialIcon(painterResource(id = R.drawable.svg_twitter), "Twitter")
                        SocialIcon(painterResource(id = R.drawable.svg_apple), "Apple")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Box(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        AnnotatedClickableText(navController)
                    }
                    Spacer(modifier = Modifier.height(40.dp))

                    //login button
                    Box(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        RoundedButton(text = "sign in") {
                            if (phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                                //navController.popBackStack()
                                viewModel.login(phoneNumber, password)
                            } else if (phoneNumber.isEmpty() && password.isEmpty()) {
                                phoneError = true
                                passwordError = true

                            } else if (phoneNumber.isEmpty()) {
                                phoneError = true
                            } else {
                                passwordError = true
                            }
                        }
                    }


                    // Error message
                    if (errorMessage != null) {
                        Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
                    }

                    // Success Response
                    if (loginResponse != null) {
                        navController.navigate("home_screen")
                    }


                }
            }
        }



    }
}

//rich text
@Composable
fun AnnotatedClickableText(navController: NavController) {
    val annotatedText = buildAnnotatedString {
        //append your initial text
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.clr5B5B5B),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("Don't have an account? ")

        }

        //Start of the pushing annotation which you want to color and make them clickable later
        pushStringAnnotation(
            tag = "SignUp",// provide tag which will then be provided when you click the text
            annotation = "SignUp"
        )
        //add text with your different color/style
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.purple7063BF),
                fontWeight = FontWeight.Medium
            )
        ) {
            append("Sign Up")
        }
        // when pop is called it means the end of annotation with current tag
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "SignUp",// tag which you used in the buildAnnotatedString
                start = offset,
                end = offset
            )[0].let {
                navController.navigate("signUp_screen")
            }
        }
    )
}

fun Modifier.hideKeyboardOnOutsideClick(): Modifier = composed {
    val controller = LocalSoftwareKeyboardController.current
    this then Modifier.noRippleClickable {
        controller?.hide()
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this then Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}



@Preview
@Composable
fun Test() {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            horizontalAlignment = Alignment.Start) {


            //Back button
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            CommonText(
                textValue = "Sign in", textStyle = TextStyle(
                    color = colorResource(id = R.color.purple7063BF),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = colorResource(id = R.color.purple7063BF), thickness = 3.dp)

            Spacer(modifier = Modifier.height(16.dp))

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
            Spacer(modifier = Modifier.height(16.dp))

            // Mobile Number Input Field
            CommonInputTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                    phoneError = it.isEmpty()
                },
                label = "Mobile Number *",
                placeholder = "+965 2601 0848",
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
                    textValue = "Password *", textStyle = TextStyle(
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
                label = "Password *",
                isPassword = true,
                isError = passwordError,
                errorMessage = "Password is required"
            )
            //forgot password
            Box(
                modifier = Modifier.align(alignment = Alignment.End)
            ) {
                CommonText(
                    textValue = "Forgot password?",
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.purple1D0330),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    )

            }


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

            // Social Login Buttons
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                SocialIcon(painterResource(id = R.drawable.svg_google), "Google")
                SocialIcon(painterResource(id = R.drawable.svg_twitter), "Twitter")
                SocialIcon(painterResource(id = R.drawable.svg_apple), "Apple")
            }

            Spacer(modifier = Modifier.height(32.dp))



        }

    }
}


@Composable
fun SocialIcon(icon: Painter, contentDescription: String) {
    Card(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.clrE8E8EC),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp,
            ),

    ) {
        Image(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

