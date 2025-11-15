package com.konkuk.artium.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun HeaderBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    title: String,
    // 스타일과 컬러를 덧씌울 수 있는 파라미터 추가
    titleStyle: TextStyle = ArtiumTheme.typography.SB_16,
    titleColor: Color = ArtiumTheme.colors.s40,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ArtiumTheme.colors.white)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .clickable { onBackClick() },
                painter = painterResource(id = R.drawable.ic_button_arrow_back),
                contentDescription = "뒤로가기",
                tint = Color.Unspecified,
            )
            Text(
                text = title,
                style = titleStyle,
                color = titleColor
            )
            Box(
                modifier = Modifier.size(24.dp)
            )
        }
        Divider(modifier = Modifier, thickness = 1.dp, color = ArtiumTheme.colors.nv80)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderBar() {
    ArtiumTheme {
        HeaderBar(
            title = "작품 쓰기",
            onBackClick = {}
        )
    }
}