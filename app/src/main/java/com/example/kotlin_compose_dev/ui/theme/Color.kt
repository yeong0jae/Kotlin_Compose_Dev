package com.example.kotlin_compose_dev.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// 사용자 정의 색상
val veryLightGrey = Color(0x60DCDCDC)
val lightGreen200 = Color(0x9932CD32)
val teal200 = Color(0xFF03DAC5)

val ColorScheme.lightGreen: Color // ColorScheme에 재정의가 아닌 lightGreen를 확장 프로퍼티로 추가
    @Composable
    get() = lightGreen200 // 확장 프로퍼티의 getter는 lightGreen200을 반환

val ColorScheme.teal: Color // ColorScheme에 재정의가 아닌 teal200를 확장 프로퍼티로 추가
    @Composable
    get() = teal200 // 확장 프로퍼티의 getter는 teal200을 반환