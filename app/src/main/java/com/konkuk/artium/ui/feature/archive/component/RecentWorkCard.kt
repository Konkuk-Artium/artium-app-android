package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.figmaShadow

@Composable
fun RecentWorkCard(
    modifier: Modifier = Modifier,
    title: String,
    //posterResId: Int,
    imageUrl: String,
    rating: Float,
    index: Int = 0,
    onCardClick: () -> Unit,
    onArrowClick: () -> Unit
) {
    val mockList = listOf(
        R.drawable.poster_tosca,
        R.drawable.poster_isabelledeganny,
        R.drawable.poster_lifeofpi,
    )
//    val gradientColors = listOf(
//        Color(0xFFFF6B6B), // 시작색 (포스터의 메인 톤)
//        Color(0xFF1E1E1E)  // 끝색 (어두운 톤)
//    )
    //val dominantColor = extractDominantColor(posterResId)
// TODO: 제목길이 ... 줄이기
    val posterResId = mockList.getOrNull(index % mockList.size) ?: R.drawable.poster_tosca
    val dominantColor = Color(0xFFE0E0E0)
    val shadow = ArtiumTheme.shadows.card
    Box(
        modifier = modifier
            .size(195.dp, 260.dp)
            .figmaShadow(shadow, cornerRadius = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onCardClick() },
        contentAlignment = Alignment.BottomCenter
    ) {
        // 🔹 Composable context 내에서 직접 painterResource 호출
        val painter = painterResource(id = posterResId)


        // 포스터 이미지
        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // 하단 오버레이 박스 (왼쪽 하얀색 → 오른쪽 dominantColor)
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
                .width(165.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // 살짝 그림자
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.95f),
                                dominantColor
                            )
                        )
                    )
            ) {
                // 제목, 별점, 버튼
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            style = ArtiumTheme.typography.SB_16,
                            color = Color.Black
                        )
                        Icon(
                            modifier = Modifier
                                .clickable { onArrowClick() },
                            painter = painterResource(id = R.drawable.ic_button_arrow_right),
                            contentDescription = "자세히 보기",
                            tint = Color.Unspecified
                        )
                    }

                    RatingBar(
                        rating = rating,
                        onRatingChange = null,
                        tint = Color.Unspecified
                    ) // TODO: 사용자가 입력한 별점이 표시 되도록 하기

                }

            }
        }

    }
}

@Preview(
    showBackground = true,
    heightDp = 500, widthDp = 300,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun RecentWorkCard() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RecentWorkCard(
            title = "오페라<토스카>",
            imageUrl = "",
            rating = 4f,
            onCardClick = {},
            onArrowClick = {}
        )
    }

}