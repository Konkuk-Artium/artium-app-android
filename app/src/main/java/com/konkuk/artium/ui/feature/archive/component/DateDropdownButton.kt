package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 📅 DateDropdownButton
 * - 관람 날짜 전용 드롭다운
 * - 작품을 선택해야 활성화됨
 */
@Composable
fun DateDropdownButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,                   // 작품 선택 여부
    options: List<String>,              // 관람 날짜 리스트
    selectedOption: String?,            // 현재 선택된 날짜
    onOptionSelected: (String) -> Unit  // 항목 클릭 시 콜백
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Transparent)
            .border(
                width = 1.dp,
                color = if (enabled) ArtiumTheme.colors.n87 else ArtiumTheme.colors.n87, // ✅ 회색 테두리 처리
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(enabled) {          // 작품이 선택된 경우에만 클릭 가능
                if (enabled) expanded = !expanded
            }
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = when {
                    !enabled -> "Date" // 비활성 시 안내 문구
                    selectedOption == null -> "관람 날짜 선택" // 기본 placeholder
                    else -> selectedOption
                },
                style = ArtiumTheme.typography.R_16.copy(fontSize = 12.sp),
                color = if (enabled) ArtiumTheme.colors.n87 else ArtiumTheme.colors.gray
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_button_arrow_down),
                contentDescription = "날짜 선택 열기",
                tint = Color.Unspecified
            )
        }
    }

    // 드롭다운 리스트
    if (enabled) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(ArtiumTheme.colors.white)
                .clip(RoundedCornerShape(12.dp))
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = ArtiumTheme.typography.R_16.copy(fontSize = 14.sp),
                            color = ArtiumTheme.colors.s40
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDateDropdownButton() {
    var selectedDate by remember { mutableStateOf<String?>(null) }

    val viewingDates = listOf("2025-11-01", "2025-11-02", "2025-11-03")

    ArtiumTheme {
        DateDropdownButton(
            modifier = Modifier,
            enabled = false,
            options = viewingDates,
            selectedOption = selectedDate,
            onOptionSelected = { selectedDate = it }
        )
    }
}
