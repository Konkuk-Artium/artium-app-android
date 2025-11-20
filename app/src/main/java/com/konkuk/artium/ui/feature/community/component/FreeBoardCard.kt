package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * '자유게시판' 카드 (목업)
 */
@Composable
fun FreeBoardCard(
    modifier: Modifier = Modifier,
    post1Title: String,
    post1Like: Int?,
    post1Comment: Int,
    post2Title: String,
    post2Like: Int?,
    post2Comment: Int,
    post3Title: String,
    post3Like: Int?,
    post3Comment: Int,
    onArrowClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFDDDDDD))
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            // 1. 섹션 헤더
            SectionHeader(
                title = "자유게시판",
                icon = painterResource(id = R.drawable.ic_freeboard), // 👈 아이콘 임의 지정 (ic_freeboard 등으로 교체)
                showArrow = true,
                onArrowClick = {}
            )

            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(), // 내부 카드 패딩
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFD0D0D0))

            ) {
                // 2. 첫 번째 글
                PostItemRow(
                    title = post1Title,
                    content = null,
                    likeCount = post1Like,
                    commentCount = post1Comment,
                )

                // 3. 구분선
                Divider(
                    modifier = Modifier, 1.dp,
                            Color (0xFFEEEEEE)
                )

                // 4. 두 번째 글
                PostItemRow(
                    title = post2Title,
                    content = null,
                    likeCount = post2Like,
                    commentCount = post2Comment,
                )

                // 5. 구분선
                Divider(
                    color = Color(0xFFEEEEEE),
                    thickness = 1.dp
                )

                // 6. 세 번째 글
                PostItemRow(
                    title = post3Title,
                    content = null,
                    likeCount = post3Like,  // " - " 표시됨
                    commentCount = post3Comment, // " - " 표시됨
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2EDED)
@Composable
private fun FreeBoardCardPreview() {
    ArtiumTheme {
        FreeBoardCard(
            post1Title = "오늘 공연 보신 분들 어땠나요?",
            post1Like = 8,
            post1Comment = 8,
            post2Title = "오늘 공연 보신 분들 어땠나요?",
            post2Like = 8,
            post2Comment = 8,
            post3Title = "전시 할인 정보 공유해요 🥳",
            post3Like = 0,
            post3Comment = 0,
            onArrowClick = {}
        )
    }
}