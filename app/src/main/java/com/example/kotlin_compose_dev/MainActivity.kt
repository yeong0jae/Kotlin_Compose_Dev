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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    // Surface는 Jetpack Compose에서 Material Design의 기본적인 배경을 제공하는 컴포저블. 컨테이너의 역할을 한다.
    Surface(
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            color = Color.Magenta,
            modifier = Modifier.wrapContentSize(
                align = Alignment.Center
                // align 의 기본값은 Alignment.Center
                // align은 자식 컴포저블이 부모 컴포저블 내에서 어떤 위치에 배치될지를 결정한다
            ) // wrapContentSize는 자식 컴포저블의 크기에 맞게 Surface의 크기를 조정한다
        ) {
            Text(
                text = "Wrapped Content", // Text는 Modifier.wrapContentSize() 를 명시할 필요가 없음
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    MainScreen()
}
