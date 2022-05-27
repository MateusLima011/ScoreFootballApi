package com.mgt.domain.model.players

import kotlinx.serialization.Serializable

@Serializable
data class StatisticsResponse(
    val get: String? = null,
    val parameters: Parameters? = null,
    val results: Int? = null,
    val paging: Paging? = null,
    val response: List<Response>? = null
)

@Serializable
data class Player(
    val id: Int? = null,
    val name: String? = null,
    val photo: String? = null
)
@Serializable
data class Response(
    val player: Player? = null,
)

@Serializable
data class Paging(
    val current: Int? = null,
    val total: Int? = null,
)



@Serializable
data class Parameters(
    val id: String? = null,
    val season: String? = null,
)
