package com.konkuk.artium.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 모든 화면에서 재사용 가능한 헤더바
 *
 * @param title 화면 타이틀
 * @param rightContent 헤더 오른쪽의 컴포넌트 (ex. 글쓰기 버튼)
 */

@Composable
fun HeaderBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    title: String,
    // 스타일과 컬러를 덧씌울 수 있는 파라미터 추가
    titleStyle: TextStyle = ArtiumTheme.typography.SB_16,
    titleColor: Color = ArtiumTheme.colors.s40,
    // ⭐ 오른쪽 컴포넌트(글쓰기 버튼 등)를 전달 받을 수 있도록 구성
    rightContent: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ArtiumTheme.colors.white)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .clickable { onBackClick() },
                painter = painterResource(id = R.drawable.ic_button_arrow_back),
                contentDescription = "뒤로가기",
                tint = Color.Unspecified,
            )
            Text(
                text = title,
                style = titleStyle,
                color = titleColor
            )
            // 오른쪽: rightContent가 있으면 표시, 없으면 빈 박스
            if (rightContent != null) {
                rightContent()
            } else {
                // 비어있으면 크기 맞추기 위한 더미
                Spacer(modifier = Modifier.size(24.dp))
            }

        }
        Divider(modifier = Modifier, thickness = 1.dp, color = ArtiumTheme.colors.nv80)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderBar() {
    ArtiumTheme {
        HeaderBar(
            title = "자유게시판",
            onBackClick = { /* 뒤로가기 */ },
            rightContent = {
                ActionButton(
                    text = "글쓰기",
                    onClick = { /* 글쓰기 이동 */ }
                )
            }
        )
    }
}