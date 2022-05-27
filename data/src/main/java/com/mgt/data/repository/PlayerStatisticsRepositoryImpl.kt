package com.mgt.data.repository

import com.mgt.data.remote.RemoteApiService
import com.mgt.domain.model.players.StatisticsResponse
import com.mgt.domain.repository.PlayerStatisticsRepository

class PlayerStatisticsRepositoryImpl(
    private val apiService: RemoteApiService
) : PlayerStatisticsRepository {
    override suspend fun getPlayersList(): StatisticsResponse =
        apiService.getPlayersList(
            authToken = "4e2fc57361a61984483d117b10b446c5",
            2,
            2021,
            1
        )
}