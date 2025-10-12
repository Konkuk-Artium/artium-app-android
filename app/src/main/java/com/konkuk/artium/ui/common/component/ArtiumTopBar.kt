package com.konkuk.artium.ui.common.component

import android.R.attr.maxLines
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

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
        Button(
            modifier = Modifier
                .width(70.dp)
                .height(35.dp),
            onClick = onActionClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ArtiumTheme.colors.primary),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(text = "작품쓰기",
                style = ArtiumTheme.typography.M_16.copy( // Pretendard Medium 14sp
                    color = ArtiumTheme.colors.white,
                    platformStyle = PlatformTextStyle(includeFontPadding = false) // 한글 잘림 방지
                )
            )
        }
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