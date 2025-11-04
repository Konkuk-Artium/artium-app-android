package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ArchiveSummaryHeader(
    modifier: Modifier = Modifier,
    thumbnailRes: Int = com.konkuk.artium.R.drawable.poster_tosca,
    workCount: Int = 12, // 올해 본 작품 수
    topGenre: String = "뮤지컬", // 가장 많이 본 장르
    popularPostText: String = "오페라 <토스카> 글이 이번 달 가장 많은 ❤️을 받았어요." // 좋아요 문구
) {
    Divider(color = ArtiumTheme.colors.nv80, thickness = 1.dp)
    Row(
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 썸네일
        Image(
            painter = painterResource(id = thumbnailRes),
            contentDescription = "Artwork Thumbnail",
            modifier = Modifier

                .size(80.dp, 100.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop// 이미지 채우기
        )
        Spacer(modifier = modifier.width(5.dp))

        // 요약 박스
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp, // 선 두께
                    color = ArtiumTheme.colors.nv80, // 테두리 색 (테마에서 연한 회색 등)
                    shape = RoundedCornerShape(12.dp) // 모서리 둥글게
                )
                .clip(RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "올해 ${workCount}편의 무대를 만나며,",
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.p10
                )
                Text(
                    text = "$topGenre 을(를) 가장 많이 기록했어요.",
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.p10
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = popularPostText,
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.nv50
                )
            }
        }
    }
    Divider(color = ArtiumTheme.colors.nv80, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
private fun ArchiveSummaryHeaderPreview() {
    ArchiveSummaryHeader()

}