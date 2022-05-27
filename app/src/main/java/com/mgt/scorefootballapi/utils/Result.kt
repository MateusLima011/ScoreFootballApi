package com.mgt.scorefootballapi.utils

sealed class Result<out T>

data class Success<out T>(val data : T) : Result<T>()

data class Failure<out T>(val error: Throwable?) : Result<T>()

class Loading<out T> : Result<T>()
