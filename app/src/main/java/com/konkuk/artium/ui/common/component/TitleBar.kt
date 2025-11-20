package com.konkuk.artium.ui.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.artium.ui.theme.ArtiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {

        // Title + TextField 영역
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Title",
                style = ArtiumTheme.typography.SB_18,
                color = ArtiumTheme.colors.s40
            )

            // 글 제목 입력 부분
            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,

                // Material3 공식 Colors
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = ArtiumTheme.colors.primary,
                    focusedTextColor = ArtiumTheme.colors.s40,
                    unfocusedTextColor = ArtiumTheme.colors.s40
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            )
        }

        // TitleBar 하단 구분선
        Divider(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = ArtiumTheme.colors.nv80
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTitleBar() {
    var title by remember { mutableStateOf("") }

    ArtiumTheme {
        TitleBar(
            value = title,
            onValueChange = { title = it }
        )
    }
}
