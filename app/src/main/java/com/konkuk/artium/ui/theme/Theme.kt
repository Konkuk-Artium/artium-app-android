package com.konkuk.artium.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.CompositionLocalProvider
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private fun artiumLightScheme(colors: ArtiumColors) = lightColorScheme(
    primary = colors.primary,
    onPrimary = colors.white,
    background = colors.bg,
    onBackground = colors.black,
    surface = colors.white,
    onSurface = colors.black
)


/* --------- ② Theme 함수: Local 제공 + MaterialTheme 세팅 --------- */
@Composable
fun ArtiumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // 기본은 우리 팔레트 사용
    colors: ArtiumColors = defaultArtiumColors,
    typography: ArtiumTypography = defaultArtiumTypography,
    // 필요 없다면 shadows 부분은 제거하거나 기본값 준비
    //shadows: ArtiumShadows = defaultArtiumShadows,
    content: @Composable () -> Unit
) {
    val m3ColorScheme = when {
        // 원하면 다이내믹 컬러로 교체 가능 (Material 컴포넌트에만 영향)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val ctx = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        }
        else -> artiumLightScheme(colors) // 기본: 우리 팔레트로 매핑
    }

    CompositionLocalProvider(
        LocalArtiumColorsProvider provides colors,
        LocalArtiumTypographyProvider provides typography,
      //  LocalArtiumShadowProvider provides shadows, // shadows 안 쓰면 이 줄 삭제
    ) {
        MaterialTheme(
            colorScheme = m3ColorScheme,
            typography = Typography, // M3용 타이포는 필요시 커스텀
            content = content
        )
    }
}

/* --------- ③ Theme 객체: MediCareCallTheme 스타일의 접근 경로 --------- */
object ArtiumTheme {
    val colors: ArtiumColors
        @Composable @ReadOnlyComposable
        get() = LocalArtiumColorsProvider.current

    val typography: ArtiumTypography
        @Composable @ReadOnlyComposable
        get() = LocalArtiumTypographyProvider.current

    val shadow: ArtiumShadows
        @Composable @ReadOnlyComposable
        get() = LocalArtiumShadowProvider.current
}
