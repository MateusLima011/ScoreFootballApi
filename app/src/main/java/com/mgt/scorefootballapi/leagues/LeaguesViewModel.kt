package com.mgt.scorefootballapi.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgt.data.buildApiService
import com.mgt.data.repository.LeagueRepositoryImpl
import com.mgt.domain.model.leagues.League
import com.mgt.domain.usecase.LeagueUseCase
import com.mgt.scorefootballapi.utils.*
import kotlinx.coroutines.launch

class LeaguesViewModel : ViewModel() {
    private val apiService by lazy { buildApiService() }
    private val leaguesUseCases =
        LeagueUseCase(LeagueRepositoryImpl(apiService))

    private val leagueMutableLiveData = MutableLiveData <Result<List<League?>>>()
    val leagueLiveData: LiveData<Result<List<League?>>> = leagueMutableLiveData

    fun getLeagueList(){
        viewModelScope.launch {
            try {
                leagueMutableLiveData.postValue(Loading())
                val response = leaguesUseCases.getLeaguesList().response
                val list: ArrayList<League?> = ArrayList()
                response?.forEach { it ->
                    list.add(it.league)
                }
                leagueMutableLiveData.postValue(Success(list))
            }catch (e: NetworkException){
                leagueMutableLiveData.postValue(Failure(e))
            } catch (e: GeneralException) {
                leagueMutableLiveData.postValue(Failure(e))
            }
            }
        }
    }
