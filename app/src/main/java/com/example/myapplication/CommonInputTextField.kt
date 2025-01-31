package com.example.myapplication
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    isPassword: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label =  { Text(label) },
            placeholder = { Text(placeholder) },
            keyboardOptions = keyboardOptions,
            isError = isError,
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.clrE8E8EC),   // Custom Focused Border
                unfocusedBorderColor = colorResource(id = R.color.clrE8E8EC), // Lighter Unfocused Border
                errorBorderColor = Color.Red, // Red Border for Error
                focusedContainerColor = Color.White, // Background Color
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            ),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = leadingIcon?.let {
                { Icon(imageVector = it, contentDescription = "Leading Icon") }
            },
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Image(
                            painter = if (!passwordVisible) painterResource(id = R.drawable.svg_eye_visible) else painterResource(id = R.drawable.svg_eye_hide),
                            contentDescription = null
                        )
                    }
                }
            } else if (trailingIcon != null && onTrailingIconClick != null) {
                {
                    IconButton(onClick = onTrailingIconClick) {
                        Icon(imageVector = trailingIcon, contentDescription = "Trailing Icon")
                    }
                }
            } else null,
            modifier = Modifier.fillMaxWidth()
        )
        if (isError) {
            Text(text = errorMessage, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp))
        }
    }
}
