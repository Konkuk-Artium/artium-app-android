package com.konkuk.artium.ui.feature.settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.navigation.ArtiumBottomBar
import com.konkuk.artium.ui.feature.settings.component.SettingMenuItem
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.Brand_BS_Black_24
import com.konkuk.artium.ui.theme.figmaShadow

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    onNavigateToProfile: () -> Unit,
    onNavigateToBookingHistory: () -> Unit
) {
    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(Color(0xFFFAFAFA)),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ArtiumTheme.colors.white)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Setting",
                        style = Brand_BS_Black_24,
                        color = ArtiumTheme.colors.primary
                    )
                }
                Divider(thickness = 1.dp, color = ArtiumTheme.colors.nv80)
            }
        },
        bottomBar = {
            ArtiumBottomBar(modifier = Modifier.navigationBarsPadding())
        },
        containerColor = ArtiumTheme.colors.bg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .figmaShadow(
                    group = ArtiumTheme.shadows.shadow03,
                    cornerRadius = 12.dp
                )
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF2F2F2))
                .border(1.dp, ArtiumTheme.colors.nv80, RoundedCornerShape(12.dp))
                .padding(10.dp),
        ) {
            // 1. 개인정보 메뉴
            SettingMenuItem(
                text = "개인정보",
                onClick = onNavigateToProfile
            )
            Spacer(modifier = modifier.padding(10.dp))
            // 2. 예매내역 메뉴
            SettingMenuItem(
                text = "예매내역",
                onClick = onNavigateToBookingHistory
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingScreenPreview() {
    ArtiumTheme {
        SettingScreen(
            onNavigateToProfile = {},
            onNavigateToBookingHistory = {}
        )
    }
}