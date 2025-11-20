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
import com.konkuk.artium.ui.feature.community.free.FreeScreen
import com.konkuk.artium.ui.feature.community.free.QnaScreen
import com.konkuk.artium.ui.feature.community.screen.ArchiveExploreDetailScreen
import com.konkuk.artium.ui.feature.community.screen.FreeBoardWritingScreen
import com.konkuk.artium.ui.feature.community.screen.FreeDetailScreen
import com.konkuk.artium.ui.feature.community.screen.QnaDetailScreen

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
        // QnA 목록
        composable("qna/list") {
            QnaScreen(
                onNavigateToDetail = { id ->
                    navController.navigate("qna/detail/$id")
                }
            )
        }

// QnA 상세
        composable(
            "qna/detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            QnaDetailScreen(
                postId = id,
                onBackClick = { navController.popBackStack() }
            )
        }

// 자유게시판 목록
        composable("free/list") {
            FreeScreen(
                onWriteClick = {
                    navController.navigate("free/write")
                },
                onPostClick = { id ->
                    navController.navigate("free/detail/$id")
                }
            )
        }

// 자유게시판 상세
        composable(
            "free/detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            FreeDetailScreen(
                postId = id,
                onBackClick = { navController.popBackStack() }
            )
        }
        // 자유게시판 글쓰기
        composable("free/write") {
            FreeBoardWritingScreen(
                onBackClick = { navController.popBackStack() },
                onSubmit = {
                    navController.popBackStack() // 저장 후 목록으로
                }
            )
        }


// 아카이브 상세 (둘러보기)
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