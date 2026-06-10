package com.cericatto.agesignals.ui.age_gate_screen

sealed interface AgeGateState {
	data object Loading : AgeGateState
	data object Allowed : AgeGateState
	data object Blocked : AgeGateState
}