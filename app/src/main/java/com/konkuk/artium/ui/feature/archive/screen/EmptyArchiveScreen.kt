package com.konkuk.artium.ui.feature.archive.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.Brand_BS_Black_24

@Composable
fun EmptyArchiveScreen(
    modifier: Modifier = Modifier
) {
    // 미기록
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Start collecting",
            style = Brand_BS_Black_24.copy(
                fontSize = 32.sp
            ),
            color = ArtiumTheme.colors.n87
        )
        Text(
            text = "My Art!",
            style = Brand_BS_Black_24.copy(
                fontSize = 72.sp
            ),
            color = ArtiumTheme.colors.n87
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmptyArchiveScreenPreview() {
    ArtiumTheme {
        EmptyArchiveScreen()
    }
}
