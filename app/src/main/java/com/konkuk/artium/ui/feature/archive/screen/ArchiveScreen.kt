package com.konkuk.artium.ui.feature.archive.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.artium.navigation.ArtiumBottomBar
import com.konkuk.artium.ui.common.component.ArtiumTopBar
import com.konkuk.artium.ui.feature.archive.component.MyArtworkList
import com.konkuk.artium.ui.feature.archive.component.RecentWorkItem
import com.konkuk.artium.ui.feature.archive.component.RecentWorkList
import com.konkuk.artium.ui.feature.archive.component.SectionTitle
import com.konkuk.artium.ui.feature.archive.component.TotalWorksBar
import com.konkuk.artium.ui.feature.archive.viewmodel.ArchiveViewModel

@Composable
fun ArchiveScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchiveViewModel = hiltViewModel(),
    onCardClick: () -> Unit,
    onArrowClick: () -> Unit,
    onButtonClick: () -> Unit,
    onNavigateToDetail: (Long) -> Unit,
    onNavigateToWriteArtWork: () -> Unit,
    onNavigateToTotalWorks: () -> Unit = {},
) {
    // 1. ViewModel의 uiState를 구독합니다. (수정됨)
    val uiState by viewModel.uiState.collectAsState()

    // 2. uiState에서 최근 작품 리스트를 가져옵니다.
    val recentWorks = uiState.recentWorks

    // 3. 화면 시작 시 데이터 로드 함수 호출 (수정됨: loadArchiveList -> loadArchives)
    LaunchedEffect(Unit) {
        viewModel.loadArchives()
    }

    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            ArtiumTopBar(
                title = "Artium",
                actionText = "작품쓰기",
                onActionClick = onNavigateToWriteArtWork
            )
        },
        bottomBar = {
            ArtiumBottomBar()
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            if (recentWorks.isEmpty()) {
                // 1. 데이터가 없을 때 -> 정중앙(Center) 배치
                EmptyArchiveScreen(
                    modifier = Modifier.align(Alignment.Center),
                    onNavigateToWriteArtWork = {}
                )
            } else {
                // 2. 데이터가 있을 때

                // -------------------------
                // 1) 최근에 본 작품
                // -------------------------
                SectionTitle(text = "최근에 본 작품")
                Spacer(Modifier.height(12.dp))

                RecentWorkList(
                    works = recentWorks.map {
                        RecentWorkItem(
                            id = it.id,
                            title = it.title,
                            imageUrl = it.imageUrl,
                            rating = it.rating
                        )
                    },
                    onCardClick = { item ->
                        onNavigateToDetail(item.id)
                    },
                    onArrowClick = { item ->
                        onNavigateToDetail(item.id)
                    }
                )

                Spacer(Modifier.height(20.dp))

                // -------------------------
                // 2) 내 작품 목록
                // -------------------------
                Column(
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                ) {
                    MyArtworkList(
                        onNavigateToDetail = { id ->
                            onNavigateToDetail(id.toLong())
                        }
                    )

                    Spacer(Modifier.height(30.dp))

                    TotalWorksBar(
                        onTotalButtonClick = onNavigateToTotalWorks
                    )
                }

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun ArchiveScreenPreview() {
    ArchiveScreen(
        onCardClick = {},
        onArrowClick = {},
        onButtonClick = {},
        onNavigateToDetail = { _ -> },
        onNavigateToWriteArtWork = { },
        onNavigateToTotalWorks = {}
    )
}