package com.konkuk.artium.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.konkuk.artium.R

data class BottomNavItem(
    val route: Route,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
)

val BottomNavItems = listOf(
    BottomNavItem(Route.Archive,   R.string.nav_archive,   R.drawable.ic_archive),
    BottomNavItem(Route.Community, R.string.nav_community, R.drawable.ic_community),
    BottomNavItem(Route.Ticket,    R.string.nav_store,    R.drawable.ic_store),
    BottomNavItem(Route.Setting,   R.string.nav_settings,   R.drawable.ic_settings),
)
