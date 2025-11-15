package com.konkuk.artium.ui.feature.archive.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun PhotoPickerBox(
    modifier: Modifier = Modifier,
    onImageSelected: (Uri?) -> Unit = {}
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // ⭐️ 2. Activity에 의존하는 코드를 LocalInspectionMode로 감쌉니다.
    val imagePickerLauncher = if (LocalInspectionMode.current.not()) {
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedImageUri = uri
            onImageSelected(uri)
        }
    } else {
        // ⭐️ 3. 프리뷰일 때는 실행하지 않는 더미 런처를 반환합니다.
        // (주의: ActivityResultLauncher는 일반적인 Composable이 아니므로,
        // 실제로는 CompositionLocal이나 별도의 State Holder 패턴을 사용해야 합니다.
        // 여기서는 에러를 막기 위해 임시로 함수 호출을 막습니다.)
        null
    }

    Box(
        modifier = modifier
            .size(80.dp, 100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF9F9F9))
            .border(1.dp, ArtiumTheme.colors.nv80, RoundedCornerShape(12.dp))
            // ⭐️ 4. 프리뷰가 아닐 때(imagePickerLauncher가 null이 아닐 때)만 클릭 이벤트 등록
            .clickable(enabled = imagePickerLauncher != null) {
                imagePickerLauncher?.launch("image/*")
            },
        contentAlignment = Alignment.Center
    ) {
        if (selectedImageUri != null) {
            //사용자가 이미지를 선택한 경우-> 미리보기 표시
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "선택된 이미지",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            // 선택전
            Icon(
                painter = painterResource(id = R.drawable.ic_photopicker),
                contentDescription = "사진 추가",
                tint = Color.Unspecified
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PhotoPickerBoxPreview() {
    ArtiumTheme {
        PhotoPickerBox()
    }
}