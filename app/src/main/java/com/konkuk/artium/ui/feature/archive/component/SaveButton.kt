package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ArtiumTheme.colors.s40,
            contentColor = ArtiumTheme.colors.white,
        ),
    ) {
        Text(
            text = "저장하기",
            style = ArtiumTheme.typography.SB_16,
        )

    }

}

@Preview
@Composable
private fun SaveButtonPreview() {
    SaveButton()
}