package com.janewaitara.gadsleaderboard.model

sealed class Result <out T>

data class Success <out T: Any>(val data: T): Result<T>()

data class Failure (val error: Throwable): Result<Nothing>()

class Loading<T> : Result<T>()