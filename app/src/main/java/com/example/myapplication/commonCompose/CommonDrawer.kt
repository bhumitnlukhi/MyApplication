package com.example.myapplication.commonCompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun CustomDrawer(
    onCloseDrawer: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Color.Transparent)
            .padding(16.dp)
    ) {
        // Profile Section
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.svg_twitter), // Replace with actual image
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Aabir Malik", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "Recommender", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Switch Button
        Button(
            onClick = { /* Handle switching */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(0xFF7B5BF2), contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Switch to Trader")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dark Mode Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dark Mode", fontSize = 16.sp, modifier = Modifier.weight(1f))
            Switch(checked = false, onCheckedChange = { /* Handle theme change */ })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation Items
        val menuItems = listOf(
            "Home" to Icons.Default.Home,
            "My Revenue" to Icons.Default.AccountBox,
            "Request Analysis" to Icons.Default.DateRange,
            "My Favorite" to Icons.Default.Favorite,
            "Settings" to Icons.Default.Settings,
            "Terms of Services" to Icons.Default.AddCircle,
            "Support" to Icons.Default.CheckCircle
        )

        menuItems.forEach { (title, icon) ->
            DrawerItem(title, icon) { onItemClick(title) }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Language Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Arabic language", fontSize = 16.sp, modifier = Modifier.weight(1f))
            Switch(checked = false, onCheckedChange = { /* Handle language change */ })
        }

        Spacer(modifier = Modifier.weight(1f))

        // Logout
        TextButton(
            onClick = { onItemClick("Logout") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.Red)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Logout", color = Color.Red, fontSize = 16.sp)
        }
    }
}

@Composable
fun DrawerItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, fontSize = 16.sp)
    }
}