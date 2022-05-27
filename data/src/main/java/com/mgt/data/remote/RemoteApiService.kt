package com.mgt.data.remote

import com.mgt.domain.model.players.StatisticsResponse
import com.mgt.domain.model.leagues.LeaguesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RemoteApiService {
    @GET("players")
    suspend fun getPlayersList(
        @Header("x-rapidapi-key") authToken: String?,
        @Query("league") league: Int,
        @Query("season") season: Int,
        @Query("page") page: Int
    ): StatisticsResponse

    @GET("leagues")
    suspend fun getLeaguesList(
        @Header("x-rapidapi-key") authToken: String?,
        @Query("country") country: String,
        @Query("season") season: Int
    ): LeaguesResponse
}