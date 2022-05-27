package com.mgt.domain.repository

import com.mgt.domain.model.players.StatisticsResponse

interface PlayerStatisticsRepository {
  suspend fun getPlayersList() : StatisticsResponse
}