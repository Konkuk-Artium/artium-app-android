package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.artium.ui.feature.community.viewmodel.Comment
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun CommentList(
    comments: List<Comment>,
    myUserName: String = "익명",       // ⭐ 본인 여부 체크용
    showDelete: Boolean = false,                // 🔥 삭제 버튼 표시 여부
    onDelete: (Int) -> Unit = {}                // 🔥 삭제 콜백 (index 전달)
) {
    Column {
        comments.forEach { comment ->
            CommentItem(
                id = comment.id,
                author = comment.author,
                time = comment.time,
                content = comment.content,

                // 댓글 작성자가 본인일 때만 삭제 버튼 표시
                showDelete = (comment.author == myUserName),

                onDeleteClick = { deletedId ->
                    onDelete(deletedId)
                }
            )
            Divider(color = ArtiumTheme.colors.nv80)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentList() {
    ArtiumTheme {
        CommentList(
            comments = listOf(
                Comment(1, "익명", "5분 전", "이 공연 진짜 최고였어요."),
                Comment(2, "다른사람", "1시간 전", "전 개인적으로 조금 아쉬웠어요.")
            )
        )

    }
}
