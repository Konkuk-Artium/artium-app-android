package com.konkuk.artium.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ArtiumNavGraph(
    navController: NavHostController,
    start: Route = Route.Archive
) {
    NavHost(navController = navController, startDestination = start.path) {
        composable(Route.Archive.path)   { /* ArchiveScreen() */ }
        composable(Route.Community.path) { /* CommunityScreen() */ }
        composable(Route.Ticket.path)    { /* TicketScreen() */ }
        composable(Route.Setting.path)   { /* SettingScreen() */ }
    }
}
