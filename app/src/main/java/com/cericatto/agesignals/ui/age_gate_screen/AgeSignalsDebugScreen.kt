package com.cericatto.agesignals.ui.age_gate_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cericatto.agesignals.result.AgeSignalResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeSignalsDebugScreen(
	onSimulate: (AgeSignalResult) -> Unit
) {
	val options = listOf(
		"VERIFIED (Adult)" to AgeSignalResult.Adult,
		"SUPERVISED (Minor, approved)" to AgeSignalResult.Minor,
		"SUPERVISED_APPROVAL_PENDING" to AgeSignalResult.Revoked,
		"SUPERVISED_APPROVAL_DENIED" to AgeSignalResult.Revoked,
		"UNKNOWN" to AgeSignalResult.Unknown,
	)

	var selected by remember { mutableStateOf(options[0]) }
	var expanded by remember { mutableStateOf(false) }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "Age Signals Debug",
			style = MaterialTheme.typography.headlineSmall,
			modifier = Modifier.padding(bottom = 32.dp)
		)

		ExposedDropdownMenuBox(
			expanded = expanded,
			onExpandedChange = { expanded = !expanded }
		) {
			OutlinedTextField(
				value = selected.first,
				onValueChange = {},
				readOnly = true,
				label = { Text("Simulated Status") },
				trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
				modifier = Modifier
					.fillMaxWidth()
					.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
			)

			ExposedDropdownMenu(
				expanded = expanded,
				onDismissRequest = { expanded = false }
			) {
				options.forEach { option ->
					DropdownMenuItem(
						text = { Text(option.first) },
						onClick = {
							selected = option
							expanded = false
						}
					)
				}
			}
		}

		Spacer(modifier = Modifier.height(24.dp))

		Button(
			onClick = { onSimulate(selected.second) },
			modifier = Modifier.fillMaxWidth()
		) {
			Text("Simulate & Restart")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun AgeSignalsDebugScreenPreview() {
	MaterialTheme {
		AgeSignalsDebugScreen(
			onSimulate = {}
		)
	}
}