package com.konkuk.artium.ui.feature.archive.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.konkuk.artium.R
import com.konkuk.artium.navigation.ArtiumBottomBar
import com.konkuk.artium.ui.common.component.ArtiumTopBar
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.feature.archive.component.RatingBar
import com.konkuk.artium.ui.theme.ArtiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyArtWorkDetailScreen(
    modifier: Modifier = Modifier,
    workId: Int, // NavGraph에서 전달받는 작품 ID
    onNavigateToEdit: (Int) -> Unit = {}, // 수정하기 화면으로 이동할 콜백
    onBackClick: () -> Unit = {}
) {
    // 별점
    var currentRating by remember { mutableStateOf(4.5f) }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            ArtiumTopBar(
                title = "My ArtWork",
                actionText = "수정하기", // 수정하기 == 작품쓰기
                onActionClick = { onNavigateToEdit(workId) }
            )
        },
        bottomBar = {
            ArtiumBottomBar(modifier = Modifier.navigationBarsPadding())
        },
        containerColor = Color(0xFFF0F0F0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            MyArtworkContent(
                rating = currentRating,
                onRatingChange = { newRating ->
                    currentRating = newRating
                },
                modifier = Modifier
                    .fillMaxWidth(),
                onBackClick = onBackClick

            )
        }
    }
}

@Composable
fun MyArtworkContent(
    rating: Float, // rating 값 받기
    onRatingChange: (Float) -> Unit, // onRatingChange 람다 받기
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {

    // "토스카를 보고 온 후기" 텍스트
    HeaderBar(
        // 1. 'My Artwork' 대신 실제 글 제목을 전달 (ViewModel에서 받아옴)
        title = "토스카를 보고 온 후기",
        titleStyle = ArtiumTheme.typography.SB_18,
        titleColor = ArtiumTheme.colors.black,
        onBackClick = onBackClick
    )

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. 포스터 이미지
        AsyncImage(
            model = R.drawable.poster_tosca, // TODO: 실제 이미지
            contentDescription = "작품 포스터",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery) // 임시 플레이스홀더
        )
        Spacer(modifier = Modifier.height(16.dp))

        // 2. 별점
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                rating = rating,
                onRatingChange = onRatingChange, // 전달받은 람다 사용
                starSize = 20.dp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = rating.toString(),
                style = ArtiumTheme.typography.M_16,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // 메인 흰색 카드
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, ArtiumTheme.colors.nv80)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 3. 후기 텍스트
                Text(
                    text = "오랜만에 가슴을 뛰게 하는 전시였다. 특히 '첨벙(A Bigger Splash)'의 실물을 마주했을 때의 전율을 잊을 수 없다.\n\n" +
                            "분명 캔버스에 멈춰있는 그림인데도, 물보라가 튀어 오르는 소리와 차가운 물의 냄새가 동시에 느껴지는 기분이었다. " +
                            "호크니는 어떻게 이 찰나의 순간을 포착해 영원처럼 보이게 만들었을까.\n\n" +
                            "그의 그림들은 단순히 풍경을 '재현'한 것이 아니라, 그가 세상을 '어떻게' 바라보는지, 그 '시선' 자체를 담아낸 것 같았다. " +
                            "아이패드로 그린 최근 작품들에서는 그의 끊임없는 실험 정신이 돋보였다. 나이와 상관없이 계속 새로운 매체를 탐구하는 모습에 큰 자극을 받았다.",
                    style = ArtiumTheme.typography.M_16,
                    lineHeight = 24.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyArtWorkDetailScreen() {
    MyArtWorkDetailScreen(
        workId = 0,
        onNavigateToEdit = { /* 프리뷰에서는 아무것도 하지 않음 */ },
        onBackClick = { }
    )
}