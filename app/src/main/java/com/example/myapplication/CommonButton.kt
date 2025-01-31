package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(id = R.color.purple7063BF), // Purple shade
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50.dp), // Fully rounded corners
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier
            .height(56.dp) // Adjust height
            .fillMaxWidth(0.8f) // 80% of screen width
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}