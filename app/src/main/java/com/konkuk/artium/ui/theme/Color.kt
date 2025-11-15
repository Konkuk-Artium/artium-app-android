package com.konkuk.artium.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val BG = Color(0xFFF2F2F2)
val dim = Color(0xFF000000)

// Primary
val primary = Color(0xFFAB3600)
val P10 = Color(0xFF390C00)

// Secondary
val S40 = Color(0xFF77574C)
val S50 = Color(0xFF926F64)
val S60 = Color(0xFFAD887C)
val S70 = Color(0xFFCAA296)


// Neutral
val N87 = Color(0xFFE4D7D4)
val N92 = Color(0xFFF3E5E2)
val N94 = Color(0xFFF8EBE7)
val N96 = Color(0xFFFEF1ED)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val E40= Color(0xFFBA1A1A)
val Gray = Color(0xFFA09CAB)

// Neutral Variant
val NV50 = Color(0xFF85736E)
val NV60 = Color(0xFFA08D87)
val NV80 = Color(0xFFD8C2BB)

@Immutable
data class ArtiumColors(
    val bg: Color,
    val dim: Color,
    val primary : Color,
    val p10 : Color,
    val e40: Color,
    val s40: Color,
    val s50: Color,
    val s60: Color,
    val s70: Color,
    val n87: Color,
    val n92: Color,
    val n94: Color,
    val n96: Color,
    val nv50: Color,
    val nv60: Color,
    val nv80: Color,
    val white: Color,
    val gray: Color,
    val black: Color
)

val defaultArtiumColors = ArtiumColors(
    bg = BG,
    dim = dim,
    primary = primary,
    p10 = P10,
    e40 = E40,
    s40 = S40,
    s50 = S50,
    s60 = S60,
    s70 = S70,
    n87 = N87,
    n92 = N92,
    n94 = N94,
    n96 = N96,
    nv50 = NV50,
    nv60 = NV60,
    nv80 = NV80,
    white = White,
    gray = Gray,
    black = Black,
)

val LocalArtiumColorsProvider = staticCompositionLocalOf { defaultArtiumColors }

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)