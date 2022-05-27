package com.mgt.data.repository

import com.mgt.data.remote.RemoteApiService
import com.mgt.domain.model.leagues.LeaguesResponse
import com.mgt.domain.repository.LeaguesRepository

class LeagueRepositoryImpl(
    private val apiService: RemoteApiService
) : LeaguesRepository {
    override suspend fun getLeaguesList(): LeaguesResponse =
        apiService.getLeaguesList(
            authToken = "4e2fc57361a61984483d117b10b446c5",
            country = "world",
            season = 2021
        )
}