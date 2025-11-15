package com.konkuk.artium.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.konkuk.artium.ui.feature.archive.screen.ArchiveScreen
import com.konkuk.artium.ui.feature.archive.screen.MyArtWorkDetailScreen
import com.konkuk.artium.ui.feature.archive.screen.MyViewedWorksScreen
import com.konkuk.artium.ui.feature.archive.screen.WritingArtworkScreen

sealed class Route(val path: String) {
    object Archive : Route("archive_route")
    object MyViewedWorks : Route("my_viewed_works_route")
    object WritingArtwork : Route("writing_artwork_route?workId={workId}") {
        // 글쓰기 모드: workId 없이 이동하는 경로 문자열 반환
        fun createWriteRoute() = "writing_artwork_route"

        // 수정 모드: workId를 인수로 받아 이동하는 경로 문자열 반환
        fun createEditRoute(workId: Int) = "writing_artwork_route?workId=$workId"
    }

    object MyArtworkDetail : Route("artwork_detail_route/{workId}") {
        fun createRoute(workId: Int) = "artwork_detail_route/$workId"
    }

    object Community : Route("community_route")
    object Ticket : Route("ticket_route")
    object Setting : Route("setting_route")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    start: Route = Route.Archive
) {
    NavHost(navController = navController, startDestination = start.path) {
        composable(Route.Archive.path) {
            ArchiveScreen(
                onCardClick = { navController.navigate(Route.MyViewedWorks.path) },
                onArrowClick = { navController.navigate(Route.MyViewedWorks.path) },
                onButtonClick = { navController.navigate(Route.MyViewedWorks.path) },
                onNavigateToWriteArtWork = {
                    navController.navigate(Route.WritingArtwork.createWriteRoute())
                },
                // ArchiveScreen에 콜백 전달
                onNavigateToDetail = { workId ->
                    navController.navigate(Route.MyArtworkDetail.createRoute(workId))
                },
            )
        }

        composable(Route.MyViewedWorks.path) {
            MyViewedWorksScreen(
                onNavigateToWriteArtWork = {
                    navController.navigate(Route.WritingArtwork.createWriteRoute())
                },
                onNavigateToDetail = { workId ->
                    // ❗️ 나중에 실제 데이터 ID로 교체해야 합니다.
                    // (지금은 임시 목업 ID 0, 1, 2...가 전달됩니다)
                    navController.navigate(Route.MyArtworkDetail.createRoute(workId))
                }
            )
        }

        composable(
            route = Route.WritingArtwork.path,
            arguments = listOf(
                navArgument("workId") {
                    type = NavType.IntType
                    defaultValue = -1 // '쓰기' 모드 기본값
                }
            )
        ) { backStackEntry ->

            val artworkId = backStackEntry.arguments?.getInt("workId") ?: -1

            WritingArtworkScreen(
                artworkId = artworkId,
                onSaveComplete = { newWorkId ->
                    navController.navigate(Route.MyArtworkDetail.createRoute(newWorkId)) {
                        popUpTo(Route.WritingArtwork.path) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = Route.MyArtworkDetail.path,
            arguments = listOf(navArgument("workId") { type = NavType.IntType })
        ) { backStackEntry ->
            val workId = backStackEntry.arguments?.getInt("workId") ?: 0
            MyArtWorkDetailScreen(
                workId = workId,
                onNavigateToEdit = { idToEdit ->
                    navController.navigate(Route.WritingArtwork.createEditRoute(idToEdit))
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}