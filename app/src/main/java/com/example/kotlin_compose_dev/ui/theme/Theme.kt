package com.example.kotlin_compose_dev.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme( // darkColorScheme은 MaterialTheme의 colorScheme를 설정하는 함수
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    surface = veryLightGrey, // surface의 color 재정의

)

private val LightColorScheme = lightColorScheme( // lightColorScheme은 MaterialTheme의 colorScheme를 설정하는 함수
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    surface = veryLightGrey
    /* Other default colors to override
    background = Color(0xFFFFFBFE),

    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // 이거 false 해야 커스텀 가능
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        // Shape도 재정의 가능
        shapes = shapes,
        content = content // MyTheme의 content로 전달된 Mainscreen이 MaterialTheme의 content로 전달됨
    )
}