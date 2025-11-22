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

import com.konkuk.artium.ui.feature.community.viewmodel.CommentUiModel
import com.konkuk.artium.ui.feature.community.viewmodel.PostDetailUiState
import com.konkuk.artium.ui.feature.community.viewmodel.QnaDetailViewModel
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * [1] Stateful Screen (껍데기)
 * - NavGraph에서 호출되는 진짜 진입점입니다.
 * - postId를 받아서 데이터를 로드하고, Content에게 전달합니다.
 */
@Composable
fun QnaDetailScreen(
    postId: Int,
    onBackClick: () -> Unit
) {
    val viewModel: QnaDetailViewModel = hiltViewModel()
    var commentInputValue by remember { mutableStateOf("") }

    LaunchedEffect(postId) {
        viewModel.load(postId)
    }

    val ui = viewModel.detail.collectAsState().value


    QnaDetailContent(
        post = ui,
        comments = ui.comments.map {
            Comment(
                id = it.commentId,
                author = it.writer,
                time = it.createdAt,
                content = it.content
            )
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
 * - 기존에 작성하신 UI 코드입니다.
 * - 이름만 Content로 변경했습니다.
 */
@Composable
fun QnaDetailContent(
    modifier: Modifier = Modifier,
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
            PostDetailHeader(
                title = post.title,
                time = post.createdAt,
                author = post.author,
                like = null // QnA는 좋아요 없음
            )

            Divider(color = ArtiumTheme.colors.nv80)

            PostBodyBox(content = post.content)

            Divider(color = ArtiumTheme.colors.nv80)

            CommentList(comments = comments)

            Spacer(Modifier.height(16.dp))

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
fun PreviewQnaDetailScreen() {
    ArtiumTheme {

        val mock = PostDetailUiState(
            id = 2,
            title = "현대미술관 신작전",
            content = "이번 전시 어땠나요?",
            author = "익명",
            createdAt = "10분 전",
            commentCount = 3,
            comments = listOf(
                CommentUiModel(1, "익명", "좋았습니다!", "5분 전"),
                CommentUiModel(2, "익명", "작품 수 적음", "30분 전"),
                CommentUiModel(3, "익명", "평일 추천", "1시간 전"),
            )
        )

        QnaDetailContent(
            post = mock,
            comments = mock.comments.map {
                Comment(it.commentId, it.writer, it.createdAt, it.content)
            },
            commentInputValue = "",
            onCommentChange = {},
            onSendComment = {},
            onBackClick = {}
        )
    }
}
