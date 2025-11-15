package com.konkuk.artium.ui.feature.archive.component

import android.R.attr.id
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme
import com.konkuk.artium.ui.theme.Brand_BS_Black_24
import kotlin.random.Random

@Composable
fun MyArtworkList(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (Int) -> Unit // 상세 화면 이동 콜백 (ID를 받음)
) {
    // 작품 더미 리스트 (서버 데이터 들어오면 대체 가능)
    val artworks = listOf(
        Triple("오페라<토스카>", "2025.09.28", R.drawable.poster_tosca),
        Triple("이자벨 드 가네 : 더 모먼츠", "2024.10.11", R.drawable.poster_isabelledeganny),
        Triple("라이브 오브 파이", "2025.10.22", R.drawable.poster_lifeofpi),
    )

    // 🔀 순서만 랜덤으로 섞기
    val shuffled = artworks.shuffled(Random(System.currentTimeMillis()))

    // 리스트 랜덤 셔플 후 3개만
    // val randomThree = artworks.shuffled(Random(System.currentTimeMillis())).take(3)


    Column(
        modifier = modifier
            .background(ArtiumTheme.colors.white),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "My Artworks",
            style = Brand_BS_Black_24,
            color = Color(0xFF424242)
        )
        Spacer(modifier = Modifier.width(16.dp))

        shuffled.forEach { (title, date, thumbnailRes) ->
            MyArtworkCard(
                workId = id, // ✅ ID 전달
                title = title,
                date = date,
                thumbnailRes = thumbnailRes,
                onCardClick = onNavigateToDetail,
            )
        }
    }
}
// ⚠️ 컴파일 오류 방지를 위한 Quadruple 정의 (실제 프로젝트에 이미 정의되어 있을 수 있음)
data class Quadruple<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)

@Preview(showBackground = true)
@Composable
fun MyArtworkListPreview() {
    MyArtworkList(
        onNavigateToDetail = { workId -> /* Preview에서는 아무것도 하지 않음 */ } // ✅ 필수 파라미터 전달
    )
}