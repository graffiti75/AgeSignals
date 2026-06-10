package com.cericatto.agesignals.ui.age_gate_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cericatto.agesignals.domain.repository.AgeSignalsRepository
import com.cericatto.agesignals.result.AgeSignalResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeGateViewModel @Inject constructor(
	private val repository: AgeSignalsRepository
) : ViewModel() {

	private val _uiState = MutableStateFlow<AgeGateState>(AgeGateState.Loading)
	val uiState: StateFlow<AgeGateState> = _uiState.asStateFlow()

	init {
		checkAgeSignal()
	}

	private fun checkAgeSignal() {
		viewModelScope.launch {
			_uiState.value = when (repository.getAgeSignal()) {
				AgeSignalResult.Revoked -> AgeGateState.Blocked
				AgeSignalResult.Unknown -> AgeGateState.Allowed
				AgeSignalResult.Adult -> AgeGateState.Allowed
				AgeSignalResult.Minor -> AgeGateState.Allowed
			}
		}
	}
}