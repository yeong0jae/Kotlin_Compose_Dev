package com.example.kotlin_compose_dev.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    medium = CutCornerShape(topEnd = 24.dp) // MaterialTheme.shpaes.medium을 CutCornerShape로 재정의
)