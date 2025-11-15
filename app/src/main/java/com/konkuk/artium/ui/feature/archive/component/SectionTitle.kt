package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme


@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = ArtiumTheme.colors.p10,
    textStyle: TextStyle = ArtiumTheme.typography.SB_16
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
        )
    }
    Divider(color = ArtiumTheme.colors.nv80, thickness = 1.dp)
}


@Preview(
    name = "SectionTitle Preview",
    showBackground = true,
    widthDp = 400
)
@Composable
private fun Preview_SectionTitle() {
    ArtiumTheme {
        SectionTitle(
            text = "최근에 본 작품"
        )
    }
}