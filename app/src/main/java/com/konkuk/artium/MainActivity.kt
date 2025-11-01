package com.konkuk.artium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.konkuk.artium.ui.feature.archive.screen.ArchiveScreen
import com.konkuk.artium.ui.theme.ArtiumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            ArtiumTheme {
                ArchiveScreen(
                    onCardClick = {},
                    onArrowClick = {}
                )
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
            onArrowClick = {}
        )
    }
}