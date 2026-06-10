package com.cericatto.agesignals.domain.repository

import com.cericatto.agesignals.result.AgeSignalResult

interface AgeSignalsRepository {
	suspend fun getAgeSignal(): AgeSignalResult
}