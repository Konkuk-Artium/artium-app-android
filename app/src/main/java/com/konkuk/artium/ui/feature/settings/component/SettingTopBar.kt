package com.konkuk.artium.ui.feature.settings.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.Brand_BS_Black_24

@Composable
fun SettingTopBar(
    title: String = "Setting",
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ArtiumTheme.colors.white)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ArtiumTheme.colors.white)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = Brand_BS_Black_24,
                color = ArtiumTheme.colors.primary
            )
        }
        Divider(modifier = Modifier, thickness = 1.dp, color = ArtiumTheme.colors.nv80)
    }
}

/* ======================== Previews ======================== */

@Preview(name = "Generic TitleActionBar", showBackground = true, widthDp = 400)
@Composable
private fun Preview_SettingTopBar() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        SettingTopBar(
            title = "Setting"
        )
    }
}