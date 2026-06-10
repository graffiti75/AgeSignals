package com.cericatto.agesignals.ui.age_gate_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock

@Composable
fun BlockedScreen() {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(16.dp),
			modifier = Modifier.padding(32.dp)
		) {
			Icon(
				imageVector = Icons.Default.Lock,
				contentDescription = null,
				modifier = Modifier.size(64.dp),
				tint = MaterialTheme.colorScheme.error
			)
			Text(
				text = "Access Restricted",
				style = MaterialTheme.typography.headlineSmall
			)
			Text(
				text = "A parent or guardian has restricted access to this app. Please ask them to review your permissions in Google Family Link.",
				style = MaterialTheme.typography.bodyMedium,
				textAlign = TextAlign.Center
			)
		}
	}
}