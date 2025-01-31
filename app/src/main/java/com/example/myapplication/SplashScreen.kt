package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.api_service.viewModel.LoginViewModel
import kotlinx.coroutines.delay

@Composable
 fun SplashScreen(navController: NavController,viewModel: LoginViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {

        // Customize the delay time
        viewModel.gettingUserName()
        delay(3000L)
            if(viewModel.userName.value != ""){
                navController.navigate("onBoarding_screen") { popUpTo("splash") { inclusive = true } }
            }
        else {
                navController.navigate("login_screen") { popUpTo("splash") { inclusive = true } }
            }

    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(10.dp))
            Image(
                painter = painterResource(id = R.drawable.mask_group_898),
                contentDescription = null
            )
            CommonText(
                textValue = stringResource(id = R.string.lorem_ipsum), textStyle = TextStyle(
                    color = Color.Black
                )
            )

        }
    }

}