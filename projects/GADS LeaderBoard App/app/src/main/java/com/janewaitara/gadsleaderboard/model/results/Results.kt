package com.janewaitara.gadsleaderboard.model.results

sealed class Result <out T>

data class Success <out T: Any>(val data: T): Result<T>()

data class Failure (val error: String): Result<Nothing>()
