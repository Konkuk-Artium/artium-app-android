package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun TotalWorksBar(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2) // 💡 Figma 배경색
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "전체보기",
                style = ArtiumTheme.typography.M_16,
                color = ArtiumTheme.colors.black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_button_arrow_right2),
                contentDescription = "전체 작품 보기",
                tint = Color.Unspecified
                )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TotalWorksBarPreview() {
    TotalWorksBar()
}
