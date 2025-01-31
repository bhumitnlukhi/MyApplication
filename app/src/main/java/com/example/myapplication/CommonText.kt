package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun CommonText(textValue : String,textStyle: TextStyle?,modifier: Modifier = Modifier) {
    Text(text = textValue, style = textStyle ?: TextStyle(
        fontSize = 24.sp,
        shadow = Shadow(
            color = Color.Blue, blurRadius = 3f)
    ),
        fontFamily = FontFamily(Font(R.font.poppins_regular, FontWeight.Medium)),
        modifier = modifier ?: Modifier.clickable { /* Handle Forgot Password */ }

    )

}


