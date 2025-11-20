package com.konkuk.artium.ui.feature.community.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.navigation.ArtiumBottomBar
import com.konkuk.artium.ui.feature.community.component.CommunityArchiveToggle
import com.konkuk.artium.ui.feature.community.component.FreeBoardCard
import com.konkuk.artium.ui.feature.community.component.PopularCard
import com.konkuk.artium.ui.feature.community.component.QnaCard
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.Brand_BS_Black_24

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    onNavigateToQnaList: () -> Unit = {},
    onNavigateToFreeList: () -> Unit = {},
    onNavigateToArchiveExplore: (Int) -> Unit = {},
) {
    var tab by remember { mutableStateOf(0) }

    val mockItems = listOf(
        Triple(1, R.drawable.poster_tosca, "오페라<토스카>"),
        Triple(2, R.drawable.poster_isabelledeganny, "이자벨 드 가네 : 모먼츠"),
        Triple(3, R.drawable.poster_gatsby, "위대한 개츠비"),

        )

    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(Color(0xFFFAFAFA)),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ArtiumTheme.colors.white)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Artium",
                        style = Brand_BS_Black_24,
                        color = ArtiumTheme.colors.primary
                    )
                }
                Divider(thickness = 1.dp, color = ArtiumTheme.colors.nv80)
            }

        },
        bottomBar = {
            ArtiumBottomBar(modifier = Modifier.navigationBarsPadding())
        },
        containerColor = ArtiumTheme.colors.bg
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            CommunityArchiveToggle(
                selectedTabIndex = tab,
                onTabSelected = { tab = it }
            )

            Column(
                modifier = Modifier
                    .background(Color(0xFFF2F2F2))
                    .padding(10.dp),
            ) {
                if (tab == 0) {
                    // 1. 개인정보 메뉴
                    PopularCard(
                        post1Title = "",
                        post1Like = 0,
                        post1Comment = 0,
                        post2Title = "",
                        post2Like = 0,
                        post2Comment = 0,
                    )
                    Spacer(modifier = modifier.padding(12.dp))
                    // 2. 예매내역 메뉴
                    QnaCard(
                        post1Title = "",
                        post1Content = "",
                        post1Comment = 0,
                        post2Title = "",
                        post2Content = "",
                        post2Comment = 0,
                        onArrowClick = onNavigateToQnaList
                    )
                    Spacer(modifier = modifier.padding(12.dp))
                    FreeBoardCard(
                        post1Title = "",
                        post1Like = null,
                        post1Comment = 0,
                        post2Title = "",
                        post2Like = null,
                        post2Comment = 0,
                        post3Title = "",
                        post3Like = null,
                        post3Comment = 0,
                        onArrowClick = onNavigateToFreeList
                    )
                } else {
                    ArchiveExploreGrid(
                        mockItems = mockItems,
                        onNavigateToDetail = { id -> onNavigateToArchiveExplore(id) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CommunityScreenPreview() {
    ArtiumTheme {
        // Preview는 navigation 함수 동작 불필요 → 빈 람다로 전달
        CommunityScreen(
        )
    }
}