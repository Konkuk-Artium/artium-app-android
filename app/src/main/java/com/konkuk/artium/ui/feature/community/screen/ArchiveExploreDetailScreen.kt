package com.konkuk.artium.ui.feature.community.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.konkuk.artium.R
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.feature.archive.component.RatingBar
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ArchiveExploreDetailScreen(
    id: Int,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            HeaderBar(
                title = "아카이빙 상세",
                onBackClick = onBackClick,
                titleStyle = ArtiumTheme.typography.SB_20,
                titleColor = ArtiumTheme.colors.primary
            )
        },
        containerColor = Color(0xFFF0F0F0)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            ArchiveExploreDetailContent(
                // TODO: id 기반으로 실제 데이터 매핑
                rating = 4.2f,
                content = dummyContent(),
                imageRes = R.drawable.poster_tosca
            )
        }
    }
}

@Composable
fun ArchiveExploreDetailContent(
    rating: Float,
    content: String,
    imageRes: Int,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = imageRes,
            contentDescription = "포스터",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingBar(
                rating = rating,
                onRatingChange = {},   // 수정 불가 → 빈 람다
                starSize = 20.dp,
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = rating.toString(),
                style = ArtiumTheme.typography.M_16,
            )
        }

        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, ArtiumTheme.colors.nv80)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = content,
                style = ArtiumTheme.typography.M_16,
                lineHeight = 24.sp,
                color = ArtiumTheme.colors.nv60
            )
        }
    }
}

fun dummyContent() =
    """
        이 작품은 감정선이 미친듯이 좋았음…
        배우들 연기도 좋고 연출도 완벽.

        전반적으로 몰입감이 엄청나다.
    """.trimIndent()
