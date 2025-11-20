package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 커뮤니티 화면의 섹션 헤더 (예: "Q&A >")
 *
 * @param title 헤더에 표시될 제목
 * @param icon 헤더 왼쪽에 표시될 아이콘 (ImageVector)
 * @param showArrow 오른쪽에 '더보기' 화살표를 표시할지 여부
 * @param onArrowClick 화살표 또는 헤더 전체 클릭 시 동작 (showArrow가 true일 때만)
 */
@Composable
fun SectionHeader(
    title: String,
    icon: Painter,
    showArrow: Boolean,
    modifier: Modifier = Modifier,
    onArrowClick: () -> Unit = {}
) {
    val rowModifier = if (showArrow) {
        // 화살표가 있으면 행 전체를 클릭 가능하게
        modifier
            .fillMaxWidth()
            .clickable { onArrowClick() }
            .padding(horizontal = 12.dp)
    } else {
        // 화살표가 없으면 단순 패딩만
        modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    }

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 1. 왼쪽 아이콘
        Icon(
            painter = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )

        // 2. 제목
        Text(
            text = title,
            // (테마에 맞는 스타일 적용)
            style = ArtiumTheme.typography.B_17,
            fontWeight = FontWeight.Bold,
            color = ArtiumTheme.colors.black,
            modifier = Modifier.padding(start = 8.dp)
        )

        // 3. 오른쪽 공간 채우기
        Spacer(modifier = Modifier.weight(1f))

        // 4. '더보기' 화살표 (선택 사항)
        if (showArrow) {
            Icon(
                modifier = modifier,
                painter = painterResource(id = R.drawable.ic_arrow_big_right),
                contentDescription = "더보기",
                tint = Color.Unspecified
            )
        }
    }
}

// --- 프리뷰 (Preview) ---

@Preview(showBackground = true, backgroundColor = 0xFFF2EDED)
@Composable
private fun SectionHeaderPreview_Popular() {
    ArtiumTheme {
        SectionHeader(
            title = "요즘 뜨는 인기글",
            icon = painterResource(id = R.drawable.ic_megaphone), // 👈 예시 ID
            showArrow = false
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2EDED)
@Composable
private fun SectionHeaderPreview_QNA() {
    ArtiumTheme {
        SectionHeader(
            title = "Q&A",
            icon = painterResource(id = R.drawable.ic_question), // 👈 예시 ID
            showArrow = true,
            onArrowClick = {}
        )
    }
}