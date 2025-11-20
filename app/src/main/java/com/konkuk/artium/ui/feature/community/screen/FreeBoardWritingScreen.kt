package com.konkuk.artium.ui.feature.community.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.konkuk.artium.ui.common.component.HeaderBar
import com.konkuk.artium.ui.common.component.TitleBar
import com.konkuk.artium.ui.feature.archive.component.SaveButton
import com.konkuk.artium.ui.feature.archive.component.WriteInputBox
import com.konkuk.artium.ui.theme.ArtiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeBoardWritingScreen(
    // artworkId가 null이면 '작성하기', null이 아니면 '수정하기' 모드
    artworkId: Int = -1,
    onBackClick: () -> Unit = {},
    onSaveComplete: (newWorkId: Int) -> Unit,
) {
    // ⭐️ 1. 필요한 상태(State) 변수들을 정의합니다.
    var titleText by remember { mutableStateOf("") }
    var reviewText by remember { mutableStateOf("") }
    // TODO: artworkId가 null이 아니면 ViewModel에서 데이터 로드하여 위 State 변수들 초기화

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            HeaderBar(
                title = "글쓰기",
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
            Spacer(modifier = Modifier.height(30.dp))

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
fun PreviewFreeBoardWritingScreen() {

    FreeBoardWritingScreen(
        artworkId = -1, // '작성하기' 모드로 프리뷰
        onSaveComplete = { newWorkId -> /* 프리뷰에서는 아무것도 하지 않음 */ },
        onBackClick = { /* 프리뷰에서는 아무것도 하지 않음 */ }
    )

}