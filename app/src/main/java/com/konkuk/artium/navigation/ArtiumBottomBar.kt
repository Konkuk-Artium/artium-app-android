package com.konkuk.artium.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.artium.R
import com.konkuk.artium.ui.theme.ArtiumTheme

@Composable
fun ArtiumBottomBar(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("My Archive", "Community", "Ticket", "Setting")
    val icons = listOf(
        R.drawable.ic_archive,
        R.drawable.ic_community,
        R.drawable.ic_store,
        R.drawable.ic_settings
    )

    val selectedColor = ArtiumTheme.colors.primary
    val unselectedColor = ArtiumTheme.colors.s40
    Column(
        modifier = modifier
        .background(ArtiumTheme.colors.white)
    ) {
       
        Divider(color = ArtiumTheme.colors.nv80, thickness = 1.dp)


        Row(
            modifier = modifier
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround//수평방향 동일한 여백 정렬
        ) {
            items.forEachIndexed { index, bottomBar ->
                val isSelected = (selectedItem == index)
                val color = if (isSelected) selectedColor else unselectedColor

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            selectedItem = index
                            // TODO: 네비게이션 로직 연결
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = "bottomBar",
                        tint = color
                    )
                    Text(
                        text = bottomBar,
                        color = color,
                        fontSize = 11.sp
                    )
                }
            }
        }
        Divider(color = ArtiumTheme.colors.nv80, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtiumBottomBarPreview() {
    MaterialTheme {
        ArtiumBottomBar()
    }
}