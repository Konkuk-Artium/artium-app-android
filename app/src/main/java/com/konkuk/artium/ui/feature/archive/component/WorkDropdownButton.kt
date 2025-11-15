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

@Composable
fun WorkDropdownButton(
    modifier: Modifier = Modifier,
    options: List<String>, // 드롭다운 메뉴 항목 리스트
    selectedOption: String?, // 현재 선택된 항목
    onOptionSelected: (String) -> Unit = {} //항목 클릭 시 콜백
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Transparent)
            .clickable { expanded = !expanded }
            .border(
                width = 1.dp,
                color = ArtiumTheme.colors.n87,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 텍스트
            Text(
                text = selectedOption ?: "Select",
                style = ArtiumTheme.typography.R_16.copy(
                    fontSize = 12.sp
                ),
                color = ArtiumTheme.colors.gray
            )

            // 버튼
            Icon(
                modifier = modifier,
                painter = painterResource(id = R.drawable.ic_button_arrow_down),
                contentDescription = "리스트 목록 열기",
                tint = Color.Unspecified
            )
        }
    }

    // 드롭다운 리스트
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
                    expanded = false // 선택 후 닫기
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkDropdownButton() {
    var selected by remember { mutableStateOf<String?>(null) }
    val reservations = listOf(
        "오페라<토스카>",
        "예매 내역 #2 (2025-10-28)",
        "예매 내역 #3 (2025-10-20)"
    )

    ArtiumTheme {
        WorkDropdownButton(
            modifier = Modifier,
            options = reservations,
            selectedOption = selected,
            onOptionSelected = { selected = it }
        )
    }
}
