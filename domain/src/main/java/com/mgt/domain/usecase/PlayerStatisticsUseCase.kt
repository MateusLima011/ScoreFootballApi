package com.mgt.domain.usecase

import com.mgt.domain.model.players.StatisticsResponse
import com.mgt.domain.repository.PlayerStatisticsRepository

class PlayerStatisticsUseCase(
    private val repository: PlayerStatisticsRepository
) {
    suspend fun getPlayersList(): StatisticsResponse {
        return repository.getPlayersList()
    }
}