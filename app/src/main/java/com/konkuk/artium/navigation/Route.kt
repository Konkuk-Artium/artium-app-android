package com.konkuk.artium.navigation

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

sealed class CommunityRoute(val route: String) {
    object CommunityHome : CommunityRoute("community/home")

    object QnaList : CommunityRoute("community/qna/list")
    object QnaDetail : CommunityRoute("community/qna/detail/{postId}")
    object QnaWrite : CommunityRoute("community/qna/write")

    object FreeList : CommunityRoute("community/free/list")
    object FreeDetail : CommunityRoute("community/free/detail/{postId}")
    object FreeWrite : CommunityRoute("community/free/write")
}
