package com.konkuk.artium.ui.feature.archive.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.common.component.TitleBar
import com.konkuk.artium.ui.feature.archive.component.DateDropdownButton
import com.konkuk.artium.ui.feature.archive.component.PhotoPickerBox
import com.konkuk.artium.ui.feature.archive.component.RatingBar
import com.konkuk.artium.ui.feature.archive.component.SaveButton
import com.konkuk.artium.ui.feature.archive.component.ViewToggleButton
import com.konkuk.artium.ui.feature.archive.component.WorkDropdownButton
import com.konkuk.artium.ui.feature.archive.component.WriteInputBox
import com.konkuk.artium.ui.theme.ArtiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingArtworkScreen(
    // artworkId가 null이면 '작성하기', null이 아니면 '수정하기' 모드
    artworkId: Int = -1,
    onBackClick: () -> Unit = {},
    onSaveComplete: (newWorkId: Int) -> Unit,
) {
    // ⭐️ 1. 필요한 상태(State) 변수들을 정의합니다.
    var titleText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Select") }
    var selectedDate by remember { mutableStateOf("Date") }
    var currentRating by remember { mutableFloatStateOf(0.0f) }
    var reviewText by remember { mutableStateOf("") }
    var isPrivate by remember { mutableStateOf(false) } // false면 전체보기, true면 나만보기

    // TODO: artworkId가 null이 아니면 ViewModel에서 데이터 로드하여 위 State 변수들 초기화

    val categories = listOf("전시", "공연", "미디어", "기타") // 카테고리 목록
    val dates = listOf("2023-01-01", "2023-03-15", "2023-07-22") // 날짜 목록 (실제로는 date picker로 선택)

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            HeaderBar(
                title = "작품 쓰기",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            // 하단 저장 버튼
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SaveButton(
                    modifier = Modifier.padding(bottom = 32.dp),
                    onClick = {
                        // 🔔 [Step 1: 데이터 저장 로직]
                        // TODO: ViewModel을 통해 현재 입력된 데이터를 서버/DB에 저장

                        // 저장소에 작품을 등록하면, ArtiumNavGraph에서 설명했던 것처럼
                        // ArchiveScreen과 MyViewedWorksScreen의 상태도 자동으로 갱신됩니다.

                        // 임시 ID (실제로는 저장 후 반환된 ID를 사용)
                        val newWorkId = 123

                        // 🔔 [Step 2: 네비게이션 이동]
                        // 저장 성공 후, NavHost에 연결된 콜백을 호출하여 상세 화면으로 이동
                        onSaveComplete(newWorkId)
                    })
            }
        },
        containerColor = ArtiumTheme.colors.white
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Title 입력 필드
            TitleBar(
                value = titleText,
                onValueChange = { titleText = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 이미지, 카테고리, 날짜 Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PhotoPickerBox()
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Category Dropdown
                    WorkDropdownButton(
                        modifier = Modifier,
                        options = categories,
                        selectedOption = selectedCategory,
                        onOptionSelected = { selectedCategory = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Date Dropdown
                    DateDropdownButton(
                        modifier = Modifier.fillMaxWidth(1f),
                        enabled = false,
                        options = dates,
                        selectedOption = selectedDate,
                        onOptionSelected = { selectedDate = it }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 별점 및 공개 범위
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 별점
                RatingBar(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    rating = currentRating,
                    onRatingChange = { newRating -> currentRating = newRating },
                    starSize = 10.dp
                )

                // 공개 범위 버튼
                ViewToggleButton(
                    selectedOption = if (isPrivate) "나만보기" else "전체보기",
                    onOptionSelected = { selectedText ->
                        // "나만보기"가 선택되었을 때만 true를 할당
                        isPrivate = (selectedText == "나만보기")
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 후기 텍스트 영역
            WriteInputBox(
                textValue = reviewText,
                onValueChange = { reviewText = it }
            )
        }
    }
}


/* ======================== Previews ======================== */

@Preview(showBackground = true)
@Composable
fun PreviewWritingArtworkScreen() {

    WritingArtworkScreen(
        artworkId = -1, // '작성하기' 모드로 프리뷰
        onSaveComplete = { newWorkId -> /* 프리뷰에서는 아무것도 하지 않음 */ },
        onBackClick = { /* 프리뷰에서는 아무것도 하지 않음 */ }
    )

}