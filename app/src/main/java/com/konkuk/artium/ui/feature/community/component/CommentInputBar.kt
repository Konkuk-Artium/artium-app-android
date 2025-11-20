package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 댓글 입력창 + 등록 버튼 UI
 *
 * TextField 외곽 테두리를 아티움 테마에 맞게 커스텀함
 * - 배경 흰색
 * - 테두리 nv80
 * - focus 시 primary 색상
 */
@Composable
fun CommentInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        /** ⚡ 댓글 입력창 (TextField) */
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    "댓글을 입력하세요",
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.nv60
                )
            },
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = ArtiumTheme.colors.nv80,
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),

            // TextField 색상 커스터마이징
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = ArtiumTheme.colors.primary,

                focusedIndicatorColor = Color.Transparent,   // 기본 밑줄 삭제
                unfocusedIndicatorColor = Color.Transparent, // 기본 밑줄 삭제
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        /** ⚡ 등록 버튼 */
        Icon(
            painter = painterResource(id = R.drawable.ic_post), // 삭제 아이콘 필요
            contentDescription = "댓글 등록",
            modifier = Modifier
                .clickable { },
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentInputBar() {
    ArtiumTheme {
        CommentInputBar(
            value = "",
            onValueChange = {},
            onSend = {}
        )
    }
}
