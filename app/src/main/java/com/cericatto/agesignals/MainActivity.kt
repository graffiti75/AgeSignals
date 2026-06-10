package com.cericatto.agesignals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.cericatto.agesignals.domain.repository.AgeSignalsRepository
import com.cericatto.agesignals.navigation.NavHostComposable
import com.cericatto.agesignals.ui.screens.MyAgeStatusScreenRoot
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var repository: AgeSignalsRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			MaterialTheme {
				// Check if we already have an override set → go straight to gate
				val hasOverride = intent.getBooleanExtra("show_gate", false)

				if (hasOverride) {
					NavHostComposable()
				} else {
					/*
					AgeSignalsDebugScreen { result ->
						(repository as? DebugAgeSignalsRepository)
							?.overrideResult(result)

						// Restart same activity but now show the gate
						val intent = Intent(this, MainActivity::class.java)
						intent.putExtra("show_gate", true)
						startActivity(intent)
						finish()
					}
					 */
					MyAgeStatusScreenRoot()
				}
			}
		}
	}
}