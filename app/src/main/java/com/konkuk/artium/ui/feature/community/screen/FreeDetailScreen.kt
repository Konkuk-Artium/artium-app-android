package com.konkuk.artium.ui.feature.community.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.feature.archive.component.PostBodyBox
import com.konkuk.artium.ui.feature.community.component.CommentInputBar
import com.konkuk.artium.ui.feature.community.component.CommentList
import com.konkuk.artium.ui.feature.community.component.PostDetailHeader
import com.konkuk.artium.ui.feature.community.viewmodel.Comment
import com.konkuk.artium.ui.feature.community.viewmodel.FreeDetailViewModel
import com.konkuk.artium.ui.feature.community.viewmodel.PostDetailUiState
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * [1] Stateful Screen (껍데기)
 * - NavGraph가 호출하는 진짜 진입점
 * - ID를 받아서 데이터를 관리하고 UI(Content)를 호출함
 */
@Composable
fun FreeDetailScreen(
    postId: Int,
    onBackClick: () -> Unit
) {
    val viewModel: FreeDetailViewModel = hiltViewModel()

    var commentInputValue by remember { mutableStateOf("") }

    LaunchedEffect(postId) {
        viewModel.load(postId)
    }

    val ui = viewModel.detail.collectAsState().value


    FreeDetailContent(
        post = ui,
        comments = ui.comments.map {
            Comment(it.commentId, it.writer, it.content, it.createdAt)
        },
        commentInputValue = commentInputValue,
        onCommentChange = { commentInputValue = it },
        onSendComment = {
            viewModel.writeComment(postId, commentInputValue)
            commentInputValue = ""
        },
        onBackClick = onBackClick
    )
}

/**
 * [2] Stateless Screen (알맹이 UI)
 * - 기존에 작성되어 있던 UI 코드
 * - 이름만 Content로 변경
 */
@Composable
fun FreeDetailContent(
    post: PostDetailUiState,
    comments: List<Comment>,
    commentInputValue: String,
    onCommentChange: (String) -> Unit,
    onSendComment: () -> Unit,
    onBackClick: () -> Unit = {},
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
                .fillMaxSize()
        ) {
            // 헤더 (자유게시판은 좋아요 표시)
            PostDetailHeader(
                title = post.title,
                time = post.createdAt,
                author = post.author,
                like = post.likeCount,
            )

            Divider(color = ArtiumTheme.colors.nv80)

            // 본문
            PostBodyBox(
                content = post.content
            )

            Divider(color = ArtiumTheme.colors.nv80)

            // 댓글 리스트
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
@Preview(showBackground = true)
@Composable
fun PreviewFreeDetailScreen() {
    ArtiumTheme {

        val mock = PostDetailUiState(
            id = 1,
            title = "전시회 추천해드립니다",
            content = "이번 주말에 갈만한 전시회 목록입니다...",
            author = "예술가",
            createdAt = "10분 전",
            likeCount = 5,
            commentCount = 2
        )

        FreeDetailContent(
            post = mock,
            comments = emptyList(),
            commentInputValue = "",
            onCommentChange = {},
            onSendComment = {},
            onBackClick = {}
        )
    }
}
