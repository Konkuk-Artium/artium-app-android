package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 자유게시판 게시글 아이템
 *
 * @param title 게시글 제목
 * @param timeAgo "5분전" 같은 경과 시간
 * @param author "익명" 또는 닉네임
 * @param likeCount 좋아요 개수
 * @param commentCount 댓글 개수
 * @param onClick 아이템 클릭 시 이벤트
 */
@Composable
fun QnaFreePostItem(
    modifier: Modifier = Modifier,
    title: String,
    timeAgo: String,
    author: String,
    likeCount: Int? = null,   // 좋아요 null이면 숨김
    commentCount: Int, // 댓글 null이면 숨김
    showLike: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(10.dp)
    ) {

        // 제목
        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = title,
            style = ArtiumTheme.typography.SB_16,
            color = ArtiumTheme.colors.black
        )

        Spacer(modifier = Modifier.height(8.dp))



        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 시간 + 이름
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$timeAgo  |  $author",
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.nv60
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 좋아요 + 댓글
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // 좋아요 아이콘 + 숫자
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // ⭐ 좋아요는 showLike == true 일 때만 보여줌
                    if (showLike) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "좋아요",
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = likeCount.toString(),
                                style = ArtiumTheme.typography.R_14,
                                color = ArtiumTheme.colors.black
                            )
                        }
                    }
                }

                // 댓글 아이콘 + 숫자
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "댓글",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = commentCount.toString(),
                        style = ArtiumTheme.typography.R_14,
                        color = ArtiumTheme.colors.black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QnaFreePostItemPreview() {
    ArtiumTheme {
        QnaFreePostItem(
            title = "오늘 공연 보신 분들 어땠나요?",
            timeAgo = "5분전",
            author = "익명",
            likeCount = 8,
            commentCount = 8
        )
    }
}
