package com.mgt.domain.repository

import com.mgt.domain.model.leagues.LeaguesResponse

interface LeaguesRepository {
    suspend fun getLeaguesList() : LeaguesResponse
}