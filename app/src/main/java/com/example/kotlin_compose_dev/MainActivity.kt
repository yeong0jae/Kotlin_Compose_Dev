package com.example.kotlin_compose_dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp

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
    Surface(
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ) {
        // 여러 개의 자식을 가질 수 있는 Row, Column, Box 컴포저블
        Row(
            modifier = Modifier.fillMaxSize(),
            // horizontalArrangement: 가로(수평) 방향에서 자식 요소들의 배치를 조정
            // 수평 방향은 배열이므로 Arrangement 라고 명명
            // SpaceAround: 자식 요소들의 양쪽에 여백을 두고 일정 간격 배치
            // SpaceBetween: 자식 요소들의 양쪽에 여백이 없이 일정 간격 배치
            // SpaceEvenly: 자식 요소들을 여백과 같은 일정 간격으로 배치
            horizontalArrangement = Arrangement.SpaceBetween,
            // verticalAlignment: 세로(수직) 방향에서 자식 요소들의 배치를 조정
            // 수직 방향에는 정렬만이 가능하므로 Alignment 라고 명명
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalColoredBar(Color.Red)
            HorizontalColoredBar(Color.Magenta)
            HorizontalColoredBar(Color.Cyan)
            HorizontalColoredBar(Color.Yellow)
            HorizontalColoredBar(Color.Blue)
        }
    }
}

// 사용자 정의 컴포저블을 이용해서 반복되는 코드를 줄일 수 있음
// 변하는 부분을 파라미터로 받아서 사용
@Composable
fun HorizontalColoredBar(color: Color) {
    Surface(
        color = color,
        modifier = Modifier
            .height(600.dp)
            .width(60.dp)
    ) { }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    MainScreen()
}
