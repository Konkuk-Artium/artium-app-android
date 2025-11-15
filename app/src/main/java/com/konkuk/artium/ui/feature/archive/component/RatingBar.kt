package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R


/**
 * 별점 표시 컴포넌트
 * - 0.5 단위 지원 (예: 3.5, 4.5 등)
 * - Figma 디자인 기반으로 별 5개 표시
 * - 각 별은 drawable 벡터 리소스 사용
 */
@Composable
fun RatingBar(
    rating: Float,                  // 평점 값 (0.0 ~ 5.0)
    onRatingChange: ((Float) -> Unit)? = null, // 🔹 null이면 표시용으로 작동
    modifier: Modifier = Modifier,
    starSize: Dp = 10.dp,           // 별 크기
    tint: Color = Color(0xFFFFD700) // 노란색 (gold)
) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            val starNumber = index + 1
            val starRes = when {
                rating >= index + 1 -> R.drawable.ic_star_filled   // 꽉 찬 별
                rating >= starNumber - 0.5f -> R.drawable.ic_star_half         // 반쪽 별
                else -> R.drawable.ic_star_unfilled                // 빈 별
            }
            val iconModifier = if (onRatingChange != null) {
                Modifier
                    .size(starSize)
                    .clickable {
                        // 같은 별을 다시 누르면 취소 (0점으로)
                        if (rating == starNumber.toFloat()) {
                            onRatingChange(0f)
                        } else {
                            onRatingChange(starNumber.toFloat())
                        }
                    }
            } else {
                Modifier.size(starSize) //클릭 불가 (표시 전용)
            }

            Icon(
                painter = painterResource(id = starRes),
                contentDescription = "별점",
                tint = tint,
                modifier = iconModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingBarPreview() {
    var currentRating by remember { mutableStateOf(3.5f) }
    RatingBar(
        rating = currentRating,
        onRatingChange = { newRating -> currentRating = newRating })
}