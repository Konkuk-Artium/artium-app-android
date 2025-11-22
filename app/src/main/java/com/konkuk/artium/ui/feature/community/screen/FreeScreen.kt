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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.artium.ui.common.component.ActionButton
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.feature.community.component.QnaFreePostItem
import com.konkuk.artium.ui.feature.community.viewmodel.CommunityViewModel
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun FreeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onWriteClick: () -> Unit = {},
    onPostClick: (Int) -> Unit = {}
) {
    val viewModel: CommunityViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    val state = viewModel.uiState.collectAsState().value

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

            // 로딩 UI
            if (state.isLoading) {
                item {
                    androidx.compose.material3.Text(
                        "로딩 중...",
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }

            // 에러 UI
            state.error?.let { errorMsg ->
                item {
                    androidx.compose.material3.Text(
                        "에러: $errorMsg",
                        color = Color.Red,
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }

            // 실제 서버 데이터 목록 표시
            items(state.posts) { post ->
                Column(
                    modifier = Modifier.border(0.dp, Color.Transparent)
                ) {
                    QnaFreePostItem(
                        title = post.title,
                        timeAgo = post.createdAt,
                        author = post.category,     // 서버 데이터 구조에 따라 수정 가능
                        likeCount = 0,              // 서버에 like 없으면 0
                        commentCount = post.commentCount,
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
fun PreviewFreeScreen() {
    ArtiumTheme {
        FreeScreen()
    }
}
