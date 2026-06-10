package com.cericatto.agesignals.data.repository

import com.cericatto.agesignals.domain.repository.AgeSignalsRepository
import com.cericatto.agesignals.result.AgeSignalResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebugAgeSignalsRepository @Inject constructor(
	private val real: AgeSignalsRepositoryImpl  // ← concrete class, not interface
) : AgeSignalsRepository {

	private var override: AgeSignalResult? = null

	fun overrideResult(result: AgeSignalResult) {
		override = result
	}

	override suspend fun getAgeSignal(): AgeSignalResult {
		return override ?: real.getAgeSignal()
	}
}