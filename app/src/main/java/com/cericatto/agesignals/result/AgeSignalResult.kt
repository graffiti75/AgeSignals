package com.cericatto.agesignals.result

sealed interface AgeSignalResult {
	data object Adult : AgeSignalResult
	data object Minor : AgeSignalResult
	data object Revoked : AgeSignalResult
	data object Unknown : AgeSignalResult
}