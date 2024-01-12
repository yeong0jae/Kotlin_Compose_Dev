package com.example.kotlin_compose_dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingText(name = "하세요")
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = "안녕 $name!",
        modifier = Modifier
            .clickable(onClick = { })
            .padding(all = 24.dp)
            .width(200.dp)
            .height(240.dp),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    GreetingText(name = "하세요")
}
