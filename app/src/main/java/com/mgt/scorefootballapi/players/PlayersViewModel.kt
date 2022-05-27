package com.mgt.scorefootballapi.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgt.data.buildApiService
import com.mgt.data.repository.PlayerStatisticsRepositoryImpl
import com.mgt.domain.model.players.Player
import com.mgt.domain.usecase.PlayerStatisticsUseCase
import com.mgt.scorefootballapi.utils.*
import kotlinx.coroutines.launch

class PlayersViewModel : ViewModel() {
    private val apiService by lazy { buildApiService() }
    private val playersUseCases =
        PlayerStatisticsUseCase(PlayerStatisticsRepositoryImpl(apiService))

    private var playerMutableLiveData = MutableLiveData<Result<List<Player?>>>()
    val playerLiveData: LiveData<Result<List<Player?>>> = playerMutableLiveData

    fun getPlayerList() {
        viewModelScope.launch {
            try {
                playerMutableLiveData.postValue(Loading())
                val response = playersUseCases.getPlayersList().response
                val list: ArrayList<Player?> = ArrayList()
                response?.forEach { it ->
                    list.add(it.player)
                }
                playerMutableLiveData.postValue(Success(list))
            } catch (e: NetworkException) {
                playerMutableLiveData.postValue(Failure(e))
            } catch (e: GeneralException) {
                playerMutableLiveData.postValue(Failure(e))
            }

        }
    }
}