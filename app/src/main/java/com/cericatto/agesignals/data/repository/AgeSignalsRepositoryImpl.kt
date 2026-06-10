package com.cericatto.agesignals.data.repository

import android.content.Context
import com.cericatto.agesignals.domain.repository.AgeSignalsRepository
import com.cericatto.agesignals.result.AgeSignalResult
import com.google.android.play.agesignals.AgeSignalsManagerFactory
import com.google.android.play.agesignals.AgeSignalsRequest
import com.google.android.play.agesignals.model.AgeSignalsVerificationStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class AgeSignalsRepositoryImpl @Inject constructor(
	@ApplicationContext private val context: Context
) : AgeSignalsRepository {

	override suspend fun getAgeSignal(): AgeSignalResult {
		return suspendCancellableCoroutine { continuation ->
			val manager = AgeSignalsManagerFactory.create(context)

			manager
				.checkAgeSignals(AgeSignalsRequest.builder().build())
				.addOnSuccessListener { result ->
					val mapped = when (result.userStatus()) {
						AgeSignalsVerificationStatus.VERIFIED -> AgeSignalResult.Adult
						AgeSignalsVerificationStatus.SUPERVISED -> AgeSignalResult.Minor
						AgeSignalsVerificationStatus.SUPERVISED_APPROVAL_PENDING -> AgeSignalResult.Revoked
						AgeSignalsVerificationStatus.SUPERVISED_APPROVAL_DENIED -> AgeSignalResult.Revoked
						AgeSignalsVerificationStatus.DECLARED -> AgeSignalResult.Adult
						AgeSignalsVerificationStatus.UNKNOWN -> AgeSignalResult.Unknown
						else -> AgeSignalResult.Unknown
					}
					continuation.resume(mapped)
				}
				.addOnFailureListener {
					continuation.resume(AgeSignalResult.Unknown)
				}
		}
	}
}