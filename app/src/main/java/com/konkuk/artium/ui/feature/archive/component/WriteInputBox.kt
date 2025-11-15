package com.konkuk.artium.ui.feature.archive.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun WriteInputBox(
    modifier: Modifier = Modifier,
    textValue: String = "",
    onValueChange: (String) -> Unit = {}
) {
    BasicTextField(
        // 상위에서 받은 textValue를 value로 직접 사용
        value = textValue,
        // 상위에서 받은 onValueChange를 그대로 호출하여 상태를 업데이트
        onValueChange = onValueChange,

        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 350.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, ArtiumTheme.colors.nv80, RoundedCornerShape(12.dp))
            .padding(16.dp),
        textStyle = ArtiumTheme.typography.M_16.copy(
            color = ArtiumTheme.colors.nv80,
            lineHeight = 22.sp
        ),
        decorationBox = { innerTextField ->
            // placeholder 체크 로직도 textValue를 사용
            if (textValue.isEmpty()) {
                Text(
                    text = "내용을 입력하세요",
                    style = ArtiumTheme.typography.R_16.copy(
                        color = ArtiumTheme.colors.nv80
                    )
                )
            }
            innerTextField()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WriteInputBoxPreview() {
    var previewText by remember { mutableStateOf("") }
    ArtiumTheme {
        WriteInputBox(
            textValue = previewText, // 상태 전달
            onValueChange = { previewText = it } // 상태 업데이트
        )
    }
}