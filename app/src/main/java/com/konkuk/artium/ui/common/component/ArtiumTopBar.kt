package com.konkuk.artium.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun ArtiumTopBar(
    title: String = "Artium",
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = Brand_BS_Black_24,
            color = ArtiumTheme.colors.primary
        )
        ActionButton(
            text = "작품쓰기",
            onClick = onActionClick
        )
    }
}

/* ======================== Previews ======================== */

@Preview(name = "Generic TitleActionBar", showBackground = true, widthDp = 400)
@Composable
private fun Preview_TitleActionBar_Generic() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        ArtiumTopBar(
            title = "Artium",
            onActionClick = {}
        )
    }
}