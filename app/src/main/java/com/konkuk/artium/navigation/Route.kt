package com.konkuk.artium.navigation


sealed class Route(val path: String) {
    object Archive : Route("archive")
    object MyViewedWorks : Route("my_viewed_works")
    object Community : Route("community")
    object Ticket : Route("ticket")
    object Setting : Route("setting")
}

