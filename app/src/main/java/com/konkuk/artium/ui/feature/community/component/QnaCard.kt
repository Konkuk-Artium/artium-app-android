package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
 * 'Q&A' 카드 (목업)
 * 내부 아이템이 또 Card로 감싸인 중첩 구조.
 */
@Composable
fun QnaCard(
    modifier: Modifier = Modifier,
    // ❗ (수정) 하드코딩 대신, 밖에서 데이터를 받음
    post1Title: String,
    post1Content: String?,
    post1Comment: Int,
    post2Title: String,
    post2Content: String?,
    post2Comment: Int,
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
                title = "Q&A",
                icon = painterResource(id = R.drawable.ic_question), // 제공된 프리뷰 아이콘 사용
                showArrow = true,
                onArrowClick = {}
            )

            // 2. 첫 번째 Q&A 아이템 (내부 카드)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp), // 내부 카드 패딩
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFD0D0D0))

            ) {
                PostItemRow(
                    title = post1Title,
                    content = post1Content,
                    likeCount = null, // Q&A는 좋아요 없음
                    commentCount = post1Comment,
                )
            }

            // 3. 두 번째 Q&A 아이템 (내부 카드)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFD0D0D0))
            ) {
                PostItemRow(
                    title = post2Title,
                    content = post2Content,
                    likeCount = null,
                    commentCount = post2Comment,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2EDED)
@Composable
private fun QnaCardPreview() {
    ArtiumTheme {
        QnaCard(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            post1Title = "좌석 관련 질문이요",
            post1Content = "2층 R석 시야 괜찮은가요? ...",
            post1Comment = 16,
            post2Title = "추천 좀 해주세요!",
            post2Content = "좌석 관련 질문이요",
            post2Comment = 16,
            onArrowClick = {}
        )
    }
}