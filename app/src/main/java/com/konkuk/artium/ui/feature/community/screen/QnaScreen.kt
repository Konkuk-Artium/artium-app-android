package com.konkuk.artium.ui.feature.community.free

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.common.component.ActionButton
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.feature.community.component. QnaFreePostItem
import com.konkuk.artium.ui.theme.ArtiumTheme

data class QnaPost(
    val id: Int,
    val title: String,
    val time: String,
    val author: String,
    val comment: Int
)

@Composable
fun QnaScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onWriteClick: () -> Unit = {},
    onPostClick: (Int) -> Unit = {}
) {

    // ⭐ Q&A 화면용 목업 데이터
    val mockList = listOf(
        QnaPost(1, "2층 R석 시야 괜찮나요?", "5분 전", "익명", 3),
        QnaPost(2, "오늘 전시 사람 많나요?", "15분 전", "익명", 1),
        QnaPost(3, "토스카 보신 분… 의상 색감 어떤가요?", "30분 전", "익명", 6),
        QnaPost(4, "공연장에서 주차 가능한가요?", "1시간 전", "익명", 2),
        QnaPost(5, "학생 할인 되는 공연 있을까요?", "1일 전", "익명", 4),
        QnaPost(6, "좌석 추천 부탁드립니다!", "3일 전", "익명", 9),
    )

    Scaffold(
        topBar = {
            HeaderBar(
                title = "Q&A",
                onBackClick = onBackClick,
                titleStyle = ArtiumTheme.typography.SB_20,   // 🔥 폰트 사이즈 변경
                titleColor = ArtiumTheme.colors.primary,      // 🔥 글씨색 변경
                rightContent = {
                    ActionButton(
                        text = "글쓰기",
                        onClick = onWriteClick
                    )
                }
            )
        },
        containerColor = ArtiumTheme.colors.bg
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFFDFDFD))
                .fillMaxSize()
        ) {
            items(mockList) { post ->
                Column(
                    modifier = Modifier
                        .border(0.dp, Color.Transparent)
                ) {
                    QnaFreePostItem(
                        title = post.title,
                        timeAgo = post.time,
                        author = post.author,
                        commentCount = post.comment,
                        showLike = false,
                        onClick = { onPostClick(post.id) }
                    )

                    Divider(
                        color = ArtiumTheme.colors.nv80,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQnaScreen() {
    ArtiumTheme {
        QnaScreen()
    }
}
