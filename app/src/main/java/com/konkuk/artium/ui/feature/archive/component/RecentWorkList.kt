package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

data class RecentWorkItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Float
)

@Composable
fun RecentWorkList(
    modifier: Modifier = Modifier,
    works: List<RecentWorkItem>,
    onCardClick: (RecentWorkItem) -> Unit,
    onArrowClick: (RecentWorkItem) -> Unit
) {

    LazyRow(
        modifier = modifier
            .background(ArtiumTheme.colors.white),
        contentPadding = PaddingValues(horizontal = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 🔹 itemsIndexed → index(순서)와 work(데이터) 둘 다 가져올 수 있음
        itemsIndexed(works) { index, work ->
            RecentWorkCard(
                title = work.title,
                imageUrl = work.imageUrl,
                rating = work.rating,
                index = index,
                onCardClick = { onCardClick(work) },
                onArrowClick = { onArrowClick(work) }
            )
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun RecentWorkListPreview() {
    val mockList = listOf(
        RecentWorkItem(0, "오페라<토스카>", "", 4.5f),
        RecentWorkItem(1, "이자벨 드 가네", "", 3.0f),
        RecentWorkItem(2, "라이프 오브 파이", "", 4.0f)
    )

    RecentWorkList(
        works = mockList,
        onCardClick = {},
        onArrowClick = {}
    )
}
