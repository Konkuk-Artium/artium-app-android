package com.konkuk.artium.navigation

sealed class Route(val path: String) {

    object Archive : Route("archive_route")

    object MyViewedWorks : Route("my_viewed_works_route")

    // -----------------------------
    //   글쓰기 / 수정 분리된 WritingArtwork
    // -----------------------------
    object WritingArtwork : Route("writing_artwork_route?workId={workId}") {

        // 글쓰기 모드: workId 없이 이동하는 경로 반환
        fun createWriteRoute() = "writing_artwork_route"

        // 수정 모드: workId 포함하여 이동
        fun createEditRoute(workId: Int) = "writing_artwork_route?workId=$workId"
    }

    // -----------------------------
    //   상세 조회
    // -----------------------------
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
