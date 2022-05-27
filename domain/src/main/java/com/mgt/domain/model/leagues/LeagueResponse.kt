package com.mgt.domain.model.leagues

import com.mgt.domain.model.players.Paging
import com.mgt.domain.model.players.Parameters
import kotlinx.serialization.Serializable

@Serializable
data class LeaguesResponse(
    val get: String? = null,
    val parameters: Parameters? = null,
    val results: Int? = null,
    val paging: Paging? = null,
    val response: List<Response>? = null
)

@Serializable
data class League(
    val id: Int? = null,
    val name: String? = null,
    val type: String? = null,
    val logo: String? = null
)

@Serializable
data class Response(
    val league: League? = null,
)

@Serializable
data class Paging(
    val current: Int? = null,
    val total: Int? = null,
)