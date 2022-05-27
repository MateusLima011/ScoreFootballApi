package com.mgt.domain.usecase

import com.mgt.domain.model.leagues.LeaguesResponse
import com.mgt.domain.repository.LeaguesRepository

class LeagueUseCase(
    private val repository: LeaguesRepository
) {
    suspend fun getLeaguesList(): LeaguesResponse{
        return repository.getLeaguesList()
    }
}