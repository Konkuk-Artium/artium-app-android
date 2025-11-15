package com.konkuk.artium.ui.feature.archive.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.navigation.ArtiumBottomBar
import com.konkuk.artium.ui.common.component.ArtiumTopBar
import com.konkuk.artium.ui.feature.archive.component.MyArtworkList
import com.konkuk.artium.ui.feature.archive.component.RecentWorkItem
import com.konkuk.artium.ui.feature.archive.component.RecentWorkList
import com.konkuk.artium.ui.feature.archive.component.SectionTitle
import com.konkuk.artium.ui.feature.archive.component.TotalWorksBar
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ArchiveScreen(
    modifier: Modifier = Modifier,
    onCardClick: (RecentWorkItem) -> Unit = {},
    onArrowClick: (RecentWorkItem) -> Unit = {},
    onButtonClick: () -> Unit ={},
    onNavigateToWriteArtWork: () -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    works: List<Work>? = null// ❗ null일 땐 목업 데이터로 대체
) {
    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .background(ArtiumTheme.colors.white)
            .navigationBarsPadding(),
        topBar = {
            ArtiumTopBar(
                title = "Artium",
                actionText = "작품쓰기",
                onActionClick = {
                    onNavigateToWriteArtWork()
                }
            )
        },
        bottomBar = {
            ArtiumBottomBar(modifier = Modifier.navigationBarsPadding())
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize().background(Color.White)
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            // 목업 데이터
            val mockList = listOf(
                RecentWorkItem(0, "오페라<토스카>", "", 4.5f),
                RecentWorkItem(1, "이자벨 드 가네", "", 3.0f),
                RecentWorkItem(2, "라이프 오브 파이", "", 4.0f)
            )
            val data = works ?: mockList

            if (data.isEmpty()) {
                // 🟤 미기록 화면
                EmptyArchiveScreen()
            } else {
                // 🟢 기록이 있는 경우

            // 섹션 제목
            SectionTitle(text = "최근에 본 작품")
            Spacer(modifier = modifier.height(12.dp))
            // 최근에 본 작품 리스트
            RecentWorkList(
                modifier = Modifier,
                works = mockList,
                onCardClick = { recentWorkItem ->
                    // RecentWorkItem에서 ID를 추출하여 상세화면 이동 콜백 호출
                    onNavigateToDetail(recentWorkItem.id)
                },
                onArrowClick = onArrowClick
            )
            Spacer(modifier = modifier.height(12.dp))

            // 내 작품 리스트
            Column (
                modifier = modifier
                    .padding(18.dp, 12.dp)

            ){
                MyArtworkList(
                    onNavigateToDetail = onNavigateToDetail
                )
                Spacer(modifier = modifier.height(30.dp))
                TotalWorksBar(
                    onTotalButtonClick= onButtonClick
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
    }

@Preview(showBackground = true)
@Composable
private fun ArchiveScreenPreview() {
    ArchiveScreen(
        onNavigateToDetail = { workId -> /* Preview */ },
        onNavigateToWriteArtWork = { /* Preview */ }
    )

}