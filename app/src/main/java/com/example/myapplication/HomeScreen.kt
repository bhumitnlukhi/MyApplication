package com.example.myapplication

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import com.example.myapplication.commonCompose.CustomDrawer
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Animation for scaling and translation effect
    val scale by animateFloatAsState(targetValue = if (drawerState.isOpen) 0.85f else 1f, label = "")
    val translationX by animateFloatAsState(targetValue = if (drawerState.isOpen) 250f else 0f, label = "")


    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,// Allows swipe to open
        modifier = Modifier.fillMaxSize() ,
        drawerBackgroundColor = Color.Transparent,
        drawerContent = {
            CustomDrawer(onCloseDrawer = { scope.launch { drawerState.close() } }) {
                Log.d("Drawer", "Clicked: $it")
            }
        }

    ) {
        // Main content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = translationX
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text("Open Drawer")
                }
            }
        }
    }
}