package com.konkuk.artium.ui.feature.community.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ArchiveExploreGrid(
    modifier: Modifier = Modifier,
    mockItems: List<Triple<Int, Int, String>>,
    onNavigateToDetail: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(mockItems) { (id, resId, title) ->
            Column(
                modifier = Modifier
                    .clickable {
                        onNavigateToDetail(id)
                    }
                    .background(Color(0xF2F2F2)),
                horizontalAlignment = Alignment.CenterHorizontally,

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

/**
 * 🔍 Preview: 실제 화면과 똑같이 그리드를 렌더링해보는 미리보기 화면
 * - mockItems 를 preview 전용으로 제공해야 함 (실제 API 없음)
 */
@Preview(showBackground = true)
@Composable
fun ArchiveExploreGridPreview() {
    ArtiumTheme {

        // ⭐ Preview용 Mock Data (실제 API 연동 전 임시 데이터)
        val mockItems = listOf(
            Triple(1, R.drawable.poster_tosca, "오페라<토스카>"),
            Triple(2, R.drawable.poster_isabelledeganny, "이자벨 드 가네 : 모먼츠"),
            Triple(3, R.drawable.poster_gatsby, "위대한 개츠비"),
            Triple(4, R.drawable.poster_lifeofpi, "라이프 오브 파이"),
            Triple(5, R.drawable.poster_werner, "워너 브롱크호스트"),
            Triple(6, R.drawable.poster_onthebeat, "<온 더 비트>"),
        )

        // 실제 UI 호출
        ArchiveExploreGrid(
            mockItems = mockItems,
            onNavigateToDetail = {}
        )
    }
}
