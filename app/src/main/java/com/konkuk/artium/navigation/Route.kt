package com.konkuk.artium.navigation

sealed interface Route {
    val path: String
    data object Archive   : Route { override val path = "archive" }
    data object Community : Route { override val path = "community" }
    data object Ticket    : Route { override val path = "ticket" }
    data object Setting   : Route { override val path = "setting" }
    // 파라미터 필요하면 data class로 확장: data class Detail(val id: Long) : Route { override val path = "detail/$id" }
}
