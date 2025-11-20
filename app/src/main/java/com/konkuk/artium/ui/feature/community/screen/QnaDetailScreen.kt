package com.konkuk.artium.ui.feature.community.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.feature.archive.component.PostBodyBox
import com.konkuk.artium.ui.feature.community.component.Comment
import com.konkuk.artium.ui.feature.community.component.CommentInputBar
import com.konkuk.artium.ui.feature.community.component.CommentList
import com.konkuk.artium.ui.feature.community.component.PostDetailHeader
import com.konkuk.artium.ui.feature.community.free.FreePost
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * Q&A 상세 화면
 * (좋아요 없음 + 댓글 가능)
 */
@Composable
fun QnaDetailScreen(
    post: FreePost,
    comments: List<Comment>,
    commentInputValue: String,
    onCommentChange: (String) -> Unit,
    onSendComment: () -> Unit,
    onBackClick: () -> Unit = {},
    onAnswerClick: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            HeaderBar(
                title = "Q&A",
                onBackClick = onBackClick,
                titleStyle = ArtiumTheme.typography.SB_20,
                titleColor = ArtiumTheme.colors.primary
            )
        },
        containerColor = ArtiumTheme.colors.white
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // 질문 헤더
            PostDetailHeader(
                title = post.title,
                time = post.time,
                author = post.author,
                like = null,         // ⭐ 좋아요 숨김
            )

            Divider(color = ArtiumTheme.colors.nv80)
            // 본문
            PostBodyBox(
                content = post.content
            )
            Divider(color = ArtiumTheme.colors.nv80)
            // 댓글 목록
            CommentList(comments = comments)

            Spacer(Modifier.height(16.dp))

            // 댓글 입력창
            CommentInputBar(
                value = commentInputValue,
                onValueChange = onCommentChange,
                onSend = onSendComment
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 1000)
@Composable
fun PreviewQnaDetailScreen() {
    ArtiumTheme {

        val mockPost = FreePost(
            id = 2,
            title = "현대미술관 신작전 보신 분 계신가요?",
            time = "10분 전",
            author = "익명",
            like = 0,
            comment = 3,
            content = "이번 전시 어땠나요? 관람 동선이나 전시 규모가 궁금합니다."
        )

        val mockComments = listOf(
            Comment(1, "익명", "5분 전", "전 정말 재밌게 봤어요! 추천합니다."),
            Comment(2, "익명", "30분 전", "작품 수는 적지만 퀄리티는 좋아요."),
            Comment(3, "익명", "1시간 전", "평일 낮에 가면 사람 거의 없어요!")
        )


        QnaDetailScreen(
            post = mockPost,
            comments = mockComments,
            commentInputValue = "",
            onCommentChange = {},
            onSendComment = {},
            onBackClick = {},
            onAnswerClick = {}
        )
    }
}
