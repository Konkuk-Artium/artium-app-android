package com.konkuk.artium.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(36.dp)
            .width(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ArtiumTheme.colors.primary),
        contentPadding = PaddingValues(horizontal = 7.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            style = ArtiumTheme.typography.R_14.copy(
                color = ArtiumTheme.colors.white,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}

/* ======================== Previews ======================== */

@Preview(name = "Artium Action Button", showBackground = true, widthDp = 200)
@Composable
private fun Preview_ActionButton() {
    ArtiumTheme {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(
                text = "작품쓰기",
                onClick = {}
            )
        }
    }
}
