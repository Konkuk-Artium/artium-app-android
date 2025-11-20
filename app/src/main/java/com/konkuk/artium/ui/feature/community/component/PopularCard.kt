package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * '요즘 뜨는 인기글' 카드 (재사용 가능)
 * [수정됨] - 하드코딩된 'title' 대신 파라미터로 받음
 */
@Composable
fun PopularCard(
    modifier: Modifier = Modifier,
    // ❗ (수정) 하드코딩 대신, 밖에서 데이터를 받음
    post1Title: String,
    post1Like: Int,
    post1Comment: Int,
    post2Title: String,
    post2Like: Int,
    post2Comment: Int
) {
    // 1. "껍데기" 카드 (바깥쪽 큰 카드)
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, ArtiumTheme.colors.nv80)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            // 2. 섹션 헤더 (이건 PopularCard에서 고정)
            SectionHeader(
                title = "요즘 뜨는 인기글",
                icon = painterResource(id = R.drawable.ic_megaphone),
                showArrow = false
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 3. 첫 번째 "내부 카드"
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFD0D0D0))
            ) {
                PostItemRow(
                    title = post1Title,
                    content = null,
                    likeCount = post1Like,
                    commentCount = post1Comment
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4. 두 번째 "내부 카드"
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFD0D0D0))
            ) {
                PostItemRow(
                    title = post2Title,
                    content = null,
                    likeCount = post2Like,
                    commentCount = post2Comment
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2EDED)
@Composable
private fun PopularCardPreview() {
    ArtiumTheme {
        PopularCard(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            post1Title = "오늘 공연 보신 분들 어땠나요?",
            post1Like = 8,
            post1Comment = 8,
            post2Title = "토스카 공연 엔딩 감동...",
            post2Like = 8,
            post2Comment = 8
        )
    }
}