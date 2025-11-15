package com.konkuk.artium.ui.feature.archive.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.navigation.ArtiumBottomBar
import com.konkuk.artium.ui.common.component.ArtiumTopBar
import com.konkuk.artium.ui.feature.archive.component.ArchiveSummaryHeader
import com.konkuk.artium.ui.theme.ArtiumTheme

data class Work(
    val id: Int,
    val title: String,
    val thumbnailUrl: String
)


@Composable
fun MyViewedWorksScreen(
    modifier: Modifier = Modifier,
    onNavigateToWriteArtWork: () -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    // ✅ 목업 리스트 (ID, 이미지 리소스, 제목) - (임시 ID 0, 1, 2... 부여)
    val mockItems = listOf(
        Triple(0, R.drawable.poster_tosca, "오페라 <토스카>"),
        Triple(1, R.drawable.poster_isabelledeganny, "이자벨 드 가네 : 모먼츠"),
        Triple(2, R.drawable.poster_gatsby, "위대한 개츠비"),
        Triple(3, R.drawable.poster_lifeofpi, "라이프 오브 파이"),
        Triple(4, R.drawable.poster_werner, "워너 브롱크호스트"),
        Triple(5, R.drawable.poster_onthebeat, "온 더 비트")
    )

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
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
                .fillMaxSize()
                .padding(innerPadding)

        ) {

            // 헤더 (포스터+집계 텍스트)
            ArchiveSummaryHeader()

            // 전체 목록
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(mockItems) { (id, resId, title) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                        onNavigateToDetail(id)
                        }
                    ) {
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = "Artwork Thumbnail",
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .clip(RoundedCornerShape(12.dp))

                        )
                        Text(
                            text = title,
                            style = ArtiumTheme.typography.R_14,
                            color = ArtiumTheme.colors.black

                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MyViewedWorksScreenPreview() {
    ArtiumTheme {
        MyViewedWorksScreen(
            onNavigateToWriteArtWork = { /* Preview */ },
            onNavigateToDetail = { /* Preview */ }
            )
    }
}
