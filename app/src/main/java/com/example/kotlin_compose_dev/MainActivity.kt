package com.example.kotlin_compose_dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_compose_dev.ui.theme.Kotlin_Compose_DevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // setContent는 Composable을 받는다. (후행 람다 문법 : {}에 Composable을 넣어줘도 됨)
            // setContent 에서 작동 가능한 Composable을 넣어줘야한다
            GreetingButton()
        }
    }
}

@Composable
// Composable은 compose에서 UI를 빌드하기 위한 하나의 단위(= 위젯)이다
// Composable 어노테이션은 아래의 함수가 UI에 표시되어야 하는 내용임을 알려줌
fun GreetingText(name: String) {
    Text( // Jetpack Compose에서 제공하는 Text Composable
        text = "Hello $name!"
    )
}

@Composable
fun GreetingButton() {
    Button(onClick = {
        // Button Composable은 첫번쨰 인자로 onClick 람다를 받는다
    }) {// Button의 후행 람다 : RowScope(수평 공간)을 차지할 Composable을 받는다
        GreetingText(name = "button") // GreetingText Composable이 실제 Button 내부의 수평 공간에 들어간다
        GreetingText(name = "button2") // 나머지 수평 공간을 차지할 Composable
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() { // Preview를 위해 setContent와 동일한 Composable을 전달해야한다
    GreetingButton()
}
