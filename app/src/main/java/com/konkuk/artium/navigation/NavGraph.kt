package com.konkuk.artium.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.artium.ui.feature.archive.screen.ArchiveScreen
import com.konkuk.artium.ui.feature.archive.screen.MyViewedWorksScreen




@Composable
fun ArtiumNavGraph(
    navController: NavHostController,
    start: Route = Route.Archive
) {
    NavHost(navController = navController, startDestination = start.path) {
        composable(Route.Archive.path) {
            ArchiveScreen(
                onCardClick = { navController.navigate(Route.MyViewedWorks.path) },
                onArrowClick = { navController.navigate(Route.MyViewedWorks.path) },
                onButtonClick = { navController.navigate(Route.MyViewedWorks.path) }
            )
        }
        composable(Route.MyViewedWorks.path) {
            MyViewedWorksScreen()
        }
    }
}