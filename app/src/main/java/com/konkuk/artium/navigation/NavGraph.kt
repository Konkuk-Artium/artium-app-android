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
import com.konkuk.artium.ui.feature.community.screen.ArchiveExploreDetailScreen
import com.konkuk.artium.ui.feature.community.screen.FreeDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    start: Route = Route.Archive
) {
    NavHost(navController = navController, startDestination = start.path) {

        // 1. 아카이브 메인 화면
        composable(Route.Archive.path) {
            ArchiveScreen(
                onCardClick = { navController.navigate(Route.MyViewedWorks.path) },
                onArrowClick = { navController.navigate(Route.MyViewedWorks.path) },
                onButtonClick = { navController.navigate(Route.MyViewedWorks.path) },
                onNavigateToWriteArtWork = {
                    navController.navigate(Route.WritingArtwork.createWriteRoute())
                },
                onNavigateToDetail = { workId ->
                    navController.navigate(Route.MyArtworkDetail.createRoute(workId.toInt()))
                },
            )
        }

        // 2. 내가 본 작품 목록
        composable(Route.MyViewedWorks.path) {
            MyViewedWorksScreen(
                onNavigateToWriteArtWork = {
                    navController.navigate(Route.WritingArtwork.createWriteRoute())
                },
                onNavigateToDetail = { workId ->
                    navController.navigate(Route.MyArtworkDetail.createRoute(workId))
                }
            )
        }

        // 3. 글쓰기 / 수정
        composable(
            route = Route.WritingArtwork.path,
            arguments = listOf(
                navArgument("workId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val workId = entry.arguments?.getInt("workId") ?: -1
            WritingArtworkScreen(
                artworkId = workId,
                onSaveComplete = { newId ->
                    navController.navigate(Route.MyArtworkDetail.createRoute(newId)) {
                        popUpTo(Route.WritingArtwork.path) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // 4. 상세 조회
        composable(
            route = Route.MyArtworkDetail.path,
            arguments = listOf(navArgument("workId") { type = NavType.IntType })
        ) { entry ->
            val workId = entry.arguments?.getInt("workId") ?: 0
            MyArtWorkDetailScreen(
                workId = workId,
                onNavigateToEdit = { idToEdit ->
                    navController.navigate(Route.WritingArtwork.createEditRoute(idToEdit))
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // 5. 자유게시판 상세
        composable(
            "free/detail/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { entry ->
            val postId = entry.arguments?.getInt("postId") ?: 0
            FreeDetailScreen(
                postId = postId,
                onBackClick = { navController.popBackStack() }
            )
        }

        // 6. 아카이브 둘러보기 상세
        composable(
            "archive/detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            ArchiveExploreDetailScreen(
                id = id,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
