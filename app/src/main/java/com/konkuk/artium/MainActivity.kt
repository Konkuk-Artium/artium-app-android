package com.konkuk.artium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.konkuk.artium.navigation.NavGraph
import com.konkuk.artium.ui.feature.archive.screen.ArchiveScreen
import com.konkuk.artium.ui.theme.ArtiumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 시스템바 영역까지 앱이 확장되도록 설정 (Edge-to-Edge)
        // 이걸 false로 해야 statusBarsPadding()이 정상 작동합니다.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 2. 상태바 아이콘 색상 설정 (중요!)
        // 배경이 흰색이니까 아이콘을 '검은색(LightStatusBars = true)'으로 바꿔야 보입니다.
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = true // true: 검은색 아이콘, false: 흰색 아이콘
        // insetsController.isAppearanceLightNavigationBars = true // (선택) 하단 네비바 아이콘도 검게

        setContent {
            ArtiumTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtiumTheme {
        ArchiveScreen(
            onCardClick = {},
            onArrowClick = {},
            onButtonClick = {},
            onNavigateToWriteArtWork = {},
            onNavigateToDetail = {}
        )
    }
}