package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 홈 화면에서 "커뮤니티"와 "아카이브 둘러보기"를 전환하는 탭 컴포넌트
 *
 * @param selectedTabIndex 현재 선택된 탭의 인덱스 (0 또는 1)
 * @param onTabSelected 탭이 클릭되었을 때 호출될 람다. (클릭된 인덱스 반환)
 * @param modifier Modifier
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityArchiveToggle(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabTitles = listOf("커뮤니티", "아카이브\n둘러보기")

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = ArtiumTheme.colors.white,
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier,
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        modifier = modifier
                            .padding(vertical = 13.dp),
                        text = title,
                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTabIndex == index)
                            ArtiumTheme.colors.primary else Color.Gray
                    )
                }
            )
        }
    }
}


// --- 프리뷰 (Preview) ---

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun CommunityArchiveTogglePreview() {
    ArtiumTheme {
        var tab by remember { mutableStateOf(0) }
        CommunityArchiveToggle(
            selectedTabIndex = tab,
            onTabSelected = { tab = it }
        )
    }
}

