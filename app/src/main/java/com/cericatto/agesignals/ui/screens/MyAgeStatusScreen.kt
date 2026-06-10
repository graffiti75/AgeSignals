package com.cericatto.agesignals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cericatto.agesignals.ui.age_gate_screen.AgeGateState
import com.cericatto.agesignals.ui.age_gate_screen.AgeGateViewModel

@Composable
fun MyAgeStatusScreenRoot(
	viewModel: AgeGateViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	MyAgeStatusScreen(uiState = uiState)
}

@Composable
fun MyAgeStatusScreen(
	uiState: AgeGateState
) {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		when (uiState) {
			AgeGateState.Loading -> {
				CircularProgressIndicator()
			}

			AgeGateState.Allowed -> {
				StatusCard(
					icon = Icons.Default.CheckCircle,
					iconTint = MaterialTheme.colorScheme.primary,
					title = "Access Allowed",
					subtitle = "Your age signal is verified."
				)
			}

			AgeGateState.Blocked -> {
				StatusCard(
					icon = Icons.Default.Lock,
					iconTint = MaterialTheme.colorScheme.error,
					title = "Access Blocked",
					subtitle = "A parent or guardian has restricted access."
				)
			}
		}
	}
}

@Composable
private fun StatusCard(
	icon: ImageVector,
	iconTint: Color,
	title: String,
	subtitle: String
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier.padding(32.dp)
	) {
		Icon(
			imageVector = icon,
			contentDescription = null,
			modifier = Modifier.size(72.dp),
			tint = iconTint
		)
		Text(
			text = title,
			style = MaterialTheme.typography.headlineSmall
		)
		Text(
			text = subtitle,
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Center
		)
	}
}

@Preview(showBackground = true)
@Composable
fun MyAgeStatusLoadingPreview() {
	MaterialTheme {
		MyAgeStatusScreen(uiState = AgeGateState.Loading)
	}
}

@Preview(showBackground = true)
@Composable
fun MyAgeStatusAllowedPreview() {
	MaterialTheme {
		MyAgeStatusScreen(uiState = AgeGateState.Allowed)
	}
}

@Preview(showBackground = true)
@Composable
fun MyAgeStatusBlockedPreview() {
	MaterialTheme {
		MyAgeStatusScreen(uiState = AgeGateState.Blocked)
	}
}