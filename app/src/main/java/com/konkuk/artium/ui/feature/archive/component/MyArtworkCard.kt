package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.LocalArtiumShadowProvider
import com.konkuk.artium.ui.theme.figmaShadow

@Composable
fun MyArtworkCard(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    thumbnailRes: Int,
    onArrowClick: () -> Unit = {},
) {
    val dominantColor = Color(0xFFE0E0E0)
    val shadow = LocalArtiumShadowProvider.current.shadow02
    Card(
        modifier = Modifier
            .fillMaxWidth()            // Y:4px blur:4px 비슷하게
                .figmaShadow(shadow, cornerRadius = 12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = ArtiumTheme.colors.white),

    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 썸네일
                Image(
                    painter = painterResource(id = thumbnailRes),
                    contentDescription = "Artwork Thumbnail",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop// 이미지 채우기
                )
                Spacer(modifier = Modifier.width(16.dp))

                // 제목 +날짜
                Column(
                    modifier = modifier
                ) {
                    Text(
                        text = title,
                        style = ArtiumTheme.typography.M_16,
                        color = ArtiumTheme.colors.black
                    )
                    Text(
                        text = date,
                        style = ArtiumTheme.typography.M_16,
                        color = ArtiumTheme.colors.black
                    )
                }
            }
            // 오른쪽 버튼

            Icon(
                modifier = modifier
                    .clickable { onArrowClick() },
                painter = painterResource(id = R.drawable.ic_button_arrow_right2),
                contentDescription = "자세히 보기",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun MyArtworkCardPreview() {

    MyArtworkCard(
        title = "오페라<토스카>",
        date = "2025.09.28",
        thumbnailRes = R.drawable.poster_tosca
    )

}