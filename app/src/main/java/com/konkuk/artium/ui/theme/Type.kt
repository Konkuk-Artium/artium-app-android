package com.konkuk.artium.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.konkuk.artium.R
import androidx.compose.ui.text.googlefonts.Font as GoogleFontRef
val artiumFontBold = FontFamily(Font(R.font.pretendard_bold))
val artiumFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val artiumFontMedium = FontFamily(Font(R.font.pretendard_medium))
val artiumFontRegular = FontFamily(Font(R.font.pretendard_regular))

// GoogleFont Provider 생성
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// 폰트 이름 지정
val bigShouldersDisplayFontName = GoogleFont("Big Shoulders Display")

val BigShouldersDisplay = FontFamily(
    Font(
        resId = R.font.bigshoulders_black,
        weight = FontWeight.Black
    )
)
val Brand_BS_Black_24 = TextStyle(
    fontFamily = BigShouldersDisplay,   // ← 이걸로!
    fontWeight = FontWeight.Black,
    fontSize = 24.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)
@Immutable
data class ArtiumTypography(
    // Title
    val B_30 : TextStyle,
    val B_28 : TextStyle,
    val B_26 : TextStyle,
    val SB_24 : TextStyle,
    val SB_22 : TextStyle,
    val B_20 : TextStyle,
    val SB_20 : TextStyle,
    val M_20 : TextStyle,

    // Body
    val SB_18 : TextStyle,
    val R_18 : TextStyle,
    val B_17 : TextStyle,
    val M_17 : TextStyle,
    val R_17 : TextStyle,
    val SB_16 : TextStyle,
    val M_16 : TextStyle,
    val R_16 : TextStyle,

    // Caption
    val R_15 : TextStyle,
    val SB_14 : TextStyle,
    val R_14 : TextStyle,
)

val defaultArtiumTypography = ArtiumTypography(
    // Title
    B_30 = TextStyle(
        fontFamily = artiumFontBold,
        fontSize = 30.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    B_28 = TextStyle(
        fontFamily = artiumFontBold,
        fontSize = 28.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    B_26 = TextStyle(
        fontFamily = artiumFontBold,
        fontSize = 26.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    SB_24 = TextStyle(
        fontFamily = artiumFontSemiBold,
        fontSize = 24.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    SB_22 = TextStyle(
        fontFamily = artiumFontSemiBold,
        fontSize = 22.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    B_20 = TextStyle(
        fontFamily = artiumFontBold,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    SB_20 = TextStyle(
        fontFamily = artiumFontSemiBold,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    M_20 = TextStyle(
        fontFamily = artiumFontMedium,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),

    // Body
    SB_18 = TextStyle(
        fontFamily = artiumFontSemiBold,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
        letterSpacing = 0.02.em
    ),
    R_18 = TextStyle(
        fontFamily = artiumFontRegular,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
    ),
    B_17 = TextStyle(
        fontFamily = artiumFontBold,
        fontSize = 17.sp,
        lineHeight = 1.6.em,
        letterSpacing = 0.02.em
    ),
    M_17 = TextStyle(
        fontFamily = artiumFontMedium,
        fontSize = 17.sp,
        lineHeight = 1.6.em,
        letterSpacing = 0.02.em
    ),
    R_17 = TextStyle(
        fontFamily = artiumFontRegular,
        fontSize = 17.sp,
        lineHeight = 1.6.em,
    ),
    SB_16 = TextStyle(
        fontFamily = artiumFontSemiBold,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
    ),
    M_16 = TextStyle(
        fontFamily = artiumFontMedium,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
    ),
    R_16 = TextStyle(
        fontFamily = artiumFontRegular,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
    ),

    // Caption
    R_15 = TextStyle(
        fontFamily = artiumFontRegular,
        fontSize = 15.sp,
        lineHeight = 1.5.em,
        letterSpacing = (0.01).em
    ),
    SB_14 = TextStyle(
        fontFamily = artiumFontSemiBold,
        fontSize = 14.sp,
        lineHeight = 1.5.em,
        letterSpacing = (0.01).em
    ),
    R_14 = TextStyle(
        fontFamily = artiumFontRegular,
        fontSize = 14.sp,
        lineHeight = 1.5.em,
        letterSpacing = (0.01).em
    )
)

val LocalArtiumTypographyProvider = staticCompositionLocalOf { defaultArtiumTypography }

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)