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
import com.konkuk.artium.ui.feature.community.component.QnaFreePostItem
import com.konkuk.artium.ui.theme.ArtiumTheme

data class FreePost(
    val id: Int,
    val title: String,
    val time: String,
    val author: String,
    val like: Int,
    val comment: Int,
    val content: String
)

@Composable
fun FreeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onWriteClick: () -> Unit = {},
    onPostClick: (Int) -> Unit = {}
) {

    val mockList = listOf(
        FreePost(1, "오늘 공연 보신 분들 어땠나요?", "5분 전", "익명", 8, 8, "본문 예시 1"),
        FreePost(2, "현대미술관 신작전 보신 분 계신가요?", "10분 전", "익명", 1, 1, "본문 예시 2"),
        FreePost(3, "어제 O O 콘서트 다녀왔는데 무대가...", "30분 전", "익명", 8, 8, "본문 예시 3"),
        FreePost(4, "티켓팅 꿀팁 정리했습니다!", "1시간 전", "익명", 20, 3, "본문 예시 4"),
        FreePost(5, "올해 꼭 보고 싶은 공연 리스트 공유해요", "1일 전", "익명", 8, 20, "본문 예시 5"),
        FreePost(6, "전시 보러 갈 때 다들 혼자 가세요?", "3일 전", "익명", 1, 2, "본문 예시 6"),
        FreePost(7, "무료 관람 가능한 전시 리스트 모음", "4일 전", "익명", 10, 8, "본문 예시 7"),
    )

    Scaffold(
        topBar = {
            HeaderBar(
                title = "자유게시판",
                onBackClick = onBackClick,
                titleStyle = ArtiumTheme.typography.SB_20,
                titleColor = ArtiumTheme.colors.primary,
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
                    modifier = Modifier.border(0.dp, Color.Transparent)
                ) {
                    QnaFreePostItem(
                        title = post.title,
                        timeAgo = post.time,
                        author = post.author,
                        likeCount = post.like,
                        commentCount = post.comment,
                        showLike = true,
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
fun PreviewFreeScreen() {
    ArtiumTheme {
        FreeScreen()
    }
}
