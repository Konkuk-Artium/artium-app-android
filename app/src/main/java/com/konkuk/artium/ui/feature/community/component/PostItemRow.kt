package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

/**
 * 게시글 목록의 한 줄을 나타내는 아이템 (0을 " - "로 표시)
 * [수정됨] - onClick 및 clickable 속성이 제거되었습니다.
 * 클릭 이벤트는 이 컴포넌트를 감싸는 Card에서 처리합니다.
 *
 * @param title 게시글 제목
 * @param content 게시글 내용 (Q&A에서만 사용, optional)
 * @param likeCount 좋아요 수 (null이면 "좋아요" 아이콘/숫자 숨김)
 * @param commentCount 댓글 수
 */
@Composable
fun PostItemRow(
    modifier: Modifier = Modifier,
    title: String,
    content: String? = null,
    likeCount: Int? = null,
    commentCount: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. 제목 & 글 내용 (왼쪽 영역)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = ArtiumTheme.typography.SB_14,
                color = ArtiumTheme.colors.darkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // 💡 '글 내용'이 있으면 표시
            if (content != null) {
                Text(
                    text = content,
                    style = ArtiumTheme.typography.R_14,
                    color = ArtiumTheme.colors.darkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // 2. 좋아요 (오른쪽 영역, 선택 사항)
        if (likeCount != null) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "좋아요",
                modifier = Modifier.padding(start = 16.dp),
                tint = Color.Unspecified
            )
            Text(
                text = if (likeCount == 0) "-" else likeCount.toString(),
                style = ArtiumTheme.typography.SB_14,
                color = ArtiumTheme.colors.black,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        // 3. 댓글 (오른쪽 영역)
        Icon(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = "댓글",
            modifier = Modifier.padding(start = 8.dp),
            tint = Color.Unspecified
        )
        Text(
            text = if (commentCount == 0) "-" else commentCount.toString(),
            style = ArtiumTheme.typography.SB_14,
            color = ArtiumTheme.colors.black,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

// --- 프리뷰 (Preview) ---
// (프리뷰에서는 클릭이 안 돼도 괜찮으므로 onClick = {} 부분을 지웁니다)

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PostItemRowPreview_Popular() {
    ArtiumTheme {
        PostItemRow(
            title = "오늘 공연 보신 분들 어땠나요?",
            content = null,
            likeCount = 8,
            commentCount = 8
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PostItemRowPreview_QNA() {
    ArtiumTheme {
        PostItemRow(
            title = "좌석 관련 질문이요",
            content = "2층 R석 시야 괜찮은가요? 시야 방해는 없는지 궁금합니다.",
            likeCount = null,
            commentCount = 8
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PostItemRowPreview_Free() {
    ArtiumTheme {
        PostItemRow(
            title = "전시 할인 정보 공유해요 🥳",
            content = null,
            likeCount = 0,
            commentCount = 0
        )
    }
}