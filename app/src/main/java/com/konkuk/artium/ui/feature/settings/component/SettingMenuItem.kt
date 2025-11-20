package com.konkuk.artium.ui.feature.settings.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 설정 화면에서 사용되는 메뉴 아이템 (예: "개인정보 >")
 *
 * @param text 표시될 텍스트 (예: "개인정보")
 * @param onClick 클릭 시 실행될 람다
 * @param modifier Modifier
 */
@Composable
fun SettingMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ArtiumTheme.colors.white),
        border = BorderStroke(1.dp, ArtiumTheme.colors.nv80)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // "개인정보", "예매내역" 텍스트
            Text(
                text = text,
                style = ArtiumTheme.typography.SB_16,
                color = ArtiumTheme.colors.black
            )
            // ">" 아이콘
            Icon(
                painter = painterResource(id = R.drawable.ic_button_arrow_right), // 재사용
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingMenuItemPreview() {
    ArtiumTheme {
        SettingMenuItem(text = "개인정보", onClick = {})
    }
}