package com.konkuk.artium.ui.feature.community.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun FreeDetailScreen(
    post: FreePost,
    comments: List<Comment>,
    commentInputValue: String,
    onCommentChange: (String) -> Unit,
    onSendComment: () -> Unit,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            HeaderBar(
                title = "자유게시판",
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
        ) {
            // 🔥 공통 헤더
            PostDetailHeader(
                title = post.title,
                time = post.time,
                author = post.author,
                like = post.like,
            )

            Divider(color = ArtiumTheme.colors.nv80)
            Spacer(Modifier.height(16.dp))
            // 🔥 본문
            PostBodyBox(
                content = post.content
            )
            Divider(color = ArtiumTheme.colors.nv80)
            // 🔥 댓글 목록
            CommentList(comments = comments)

            Spacer(Modifier.height(16.dp))

            // 🔥 댓글 입력창
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
fun PreviewFreeDetailScreen() {
    ArtiumTheme {

        val mockPost = FreePost(
            id = 1,
            title = "오늘 공연 보신 분들 어땠나요?",
            time = "5분 전",
            author = "익명",
            like = 8,
            comment = 8,
            content = """
                오늘 공연 진짜 재밌었어요!
                무대 연출도 좋았고 배우님들 연기도 최고였습니다.

                혹시 후기 더 있으신 분들 공유해주세요 :)
            """.trimIndent()
        )

        val mockComments = listOf(
            Comment(1, "익명", "5분 전", "이 공연 진짜 최고였어요."),
            Comment(2, "익명", "1시간 전", "전 개인적으로 조금 아쉬웠어요."),
            Comment(3, "익명", "2시간 전", "티켓팅 꿀팁 더 알려주세요!")
        )


        FreeDetailScreen(
            post = mockPost,
            comments = mockComments,
            commentInputValue = "",
            onCommentChange = {},
            onSendComment = {},
            onBackClick = {}
        )
    }
}
