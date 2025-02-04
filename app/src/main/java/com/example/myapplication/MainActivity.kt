package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {

        //splash screen
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        //onboarding screen
        composable("onBoarding_screen") {
            OnBoarding(navController = navController)
        }

        //login screen
        composable("login_screen") {
            LoginScreen(navController = navController)
        }

        //signUp screen
        composable("signUp_screen") {
            SignUpScreen(navController = navController)
        }

        //otp screen
        composable("otp_screen/{mobileNumber}") {
                navBackStackEntry ->
            val phoneNumber =
                navBackStackEntry.arguments?.getString("mobileNumber") ?: error("Missing mobile number argument")
            OtpScreen(navController = navController,phoneNumber = phoneNumber)
        }

        //Home screen
        composable("home_screen") {
            HomeScreen(navController = navController)
        }

    }
}

