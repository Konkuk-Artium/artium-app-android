package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 완성된 본문(읽기전용) 박스 UI
 * 글쓰기 박스와 동일한 스타일 but Text 출력용
 */
@Composable
fun PostBodyBox(
    content: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = content,
        style = ArtiumTheme.typography.R_16.copy(
            color = ArtiumTheme.colors.black,
            lineHeight = 22.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPostBodyBox() {
    ArtiumTheme {
        PostBodyBox(
            content = "여기에 본문 내용이 표시됩니다.\n" +
                    "글쓰기 화면과 동일한 스타일로 박스 안에 출력됩니다.\n" +
                    "줄바꿈, 긴 내용, 여러 문단 모두 잘 표시됩니다."
        )
    }
}
