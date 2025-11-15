package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ViewToggleButton(
    modifier: Modifier = Modifier,
    selectedOption: String,                  // 현재 선택된 항목 ("전체보기" or "나만보기")
    onOptionSelected: (String) -> Unit       // 클릭 시 상태 변경 콜백
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(9.dp))
            .background(ArtiumTheme.colors.n87) // 전체 배경색 (연한 베이지)
            .padding(2.dp)
    ) {

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // 버튼 사이 여백
        ) {
            // 전체보기
            ToggleChip(
                text = "전체보기",
                isSelected = selectedOption == "전체보기",
                onClick = { onOptionSelected("전체보기") }
            )

            // 나만보기
            ToggleChip(
                text = "나만보기",
                isSelected = selectedOption == "나만보기",
                onClick = { onOptionSelected("나만보기") }
            )
        }
    }
}

/**
 * 🟤 ToggleChip
 * - 선택 여부에 따라 색상·배경이 바뀌는 버튼 컴포넌트
 */
@Composable
fun ToggleChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(9.dp))
            .background(
                if (isSelected) ArtiumTheme.colors.white   // 선택 시 진한 배경
                else ArtiumTheme.colors.n87          // 비활성 시 옅은 배경
            )
            .clickable { onClick() }
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = ArtiumTheme.typography.R_14.copy(
                fontSize = 8.sp
            ),
            color = if (isSelected)
                ArtiumTheme.colors.s40
            else ArtiumTheme.colors.white
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewToggleButton() {
    var selected by remember { mutableStateOf("전체보기") }

    ArtiumTheme {
        ViewToggleButton(
            selectedOption = selected,
            onOptionSelected = { selected = it }
        )
    }
}
