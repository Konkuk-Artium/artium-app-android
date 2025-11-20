package com.konkuk.artium.ui.feature.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun PostDetailHeader(
    title: String,
    time: String,
    author: String,
    like: Int? = null,   // null이면 좋아요 숨김
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = title,
            style = ArtiumTheme.typography.SB_20,
            color = ArtiumTheme.colors.black
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "$time  |  $author",
            style = ArtiumTheme.typography.R_14,
            color = ArtiumTheme.colors.nv60
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 좋아요 (옵션)
            like?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = it.toString(),
                        style = ArtiumTheme.typography.R_14,
                        color = ArtiumTheme.colors.black
                    )
                }
            }


        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewPostDetailHeader() {
    ArtiumTheme {
        PostDetailHeader(
            title = "오늘 공연 보신 분들 어땠나요?",
            time = "5분 전",
            author = "익명",
            like = 10,

            )
    }
}

