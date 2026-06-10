package com.cericatto.agesignals.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cericatto.agesignals.ui.age_gate_screen.AgeGateState
import com.cericatto.agesignals.ui.age_gate_screen.AgeGateViewModel

@Composable
fun CheckAgeScreenRoot(
	viewModel: AgeGateViewModel = hiltViewModel(),
	onAllowed: () -> Unit,
	onBlocked: () -> Unit
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	LaunchedEffect(uiState) {
		when (uiState) {
			AgeGateState.Allowed -> onAllowed()
			AgeGateState.Blocked -> onBlocked()
			AgeGateState.Loading -> Unit
		}
	}

	CheckAgeScreen(uiState = uiState)
}

@Composable
fun CheckAgeScreen(
	uiState: AgeGateState
) {
	AnimatedVisibility(visible = uiState == AgeGateState.Loading) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
	}
}

@Preview(showBackground = true)
@Composable
fun CheckAgeScreenLoadingPreview() {
	MaterialTheme {
		CheckAgeScreen(uiState = AgeGateState.Loading)
	}
}

@Preview(showBackground = true)
@Composable
fun CheckAgeScreenAllowedPreview() {
	MaterialTheme {
		CheckAgeScreen(uiState = AgeGateState.Allowed)
	}
}