package com.example.haetalhae3.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*

// 1) 컬러 팔레트 직접 정의 (예: 주 색상, 보조 색상 등)
private val PrimaryColor = Color(0xFF00695C)    // 예: 청록색
private val PrimaryVariant = Color(0xFF004D40)
private val SecondaryColor = Color(0xFF80CBC4)

// 2) 컬러 스킴 정의
private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    onPrimary = Color.White,
    // ... 필요하면 더 정의
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    onPrimary = Color.Black,
    // ... 필요하면 더 정의
)

// 3) 타이포그래피 정의 (폰트, 크기, 굵기 등)
private val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    // 필요에 따라 더 추가 가능
)

// 4) 테마 컴포저블 함수 (앱 전체에 적용)
@Composable
fun Haetalhae3Theme(
    darkTheme: Boolean = false,  // 다크 모드 자동 지원 가능
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = Shapes(), // 기본 shape 커스텀 가능
        content = content
    )
}