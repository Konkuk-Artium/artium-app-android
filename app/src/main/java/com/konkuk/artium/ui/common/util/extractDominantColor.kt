package com.konkuk.artium.ui.common.util

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette

@Composable
fun extractDominantColor(posterResId: Int): Color {
    val context = LocalContext.current
    val bitmap = BitmapFactory.decodeResource(context.resources, posterResId)

    val palette = remember(bitmap) { Palette.from(bitmap).generate() }

    val dominantColor = palette.getDominantColor(android.graphics.Color.GRAY)
    return Color(dominantColor)
}
