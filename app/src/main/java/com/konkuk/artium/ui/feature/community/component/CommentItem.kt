package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun CommentItem(
    id: Int,
    author: String,
    time: String,
    content: String,
    showDelete: Boolean = false,   // 삭제 버튼 표시 여부
    onDeleteClick: (Int) -> Unit = {}      // 삭제 클릭 이벤트
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {

        /** 🔥 작성자 + 시간 + 삭제 버튼 */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 왼쪽: 작성자 + 시간
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = author,
                    style = ArtiumTheme.typography.SB_14,
                    color = ArtiumTheme.colors.black
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = time,
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.nv60
                )
            }

            /** ⭐ 삭제 버튼 (본인 댓글일 때만 표시) */
            if (showDelete) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete), // 삭제 아이콘 필요
                    contentDescription = "댓글 삭제",
                    modifier = Modifier
                        .clickable { onDeleteClick(id) },
                    tint = ArtiumTheme.colors.nv60
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        /** 🔥 본문 */
        Text(
            text = content,
            style = ArtiumTheme.typography.R_14,
            color = ArtiumTheme.colors.black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentItem() {
    ArtiumTheme {
        Column {
            CommentItem(
                id = 1,
                author = "익명",
                time = "2시간 전",
                content = "전시 너무 좋았어요! 다음엔 같이 가요 😄",
                showDelete = true
            )
        }
    }
}
