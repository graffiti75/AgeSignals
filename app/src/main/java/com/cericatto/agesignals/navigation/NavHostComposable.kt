package com.cericatto.agesignals.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cericatto.agesignals.ui.screens.BlockedScreen
import com.cericatto.agesignals.ui.screens.CheckAgeScreenRoot

@Composable
fun NavHostComposable(
	modifier: Modifier = Modifier
) {
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = Route.CheckAgeScreen
	) {
		composable<Route.CheckAgeScreen> {
			CheckAgeScreenRoot(
				onAllowed = {
					navController.navigate(Route.MainScreen) {
						popUpTo<Route.CheckAgeScreen> { inclusive = true }
					}
				},
				onBlocked = {
					navController.navigate(Route.BlockedScreen) {
						popUpTo<Route.CheckAgeScreen> { inclusive = true }
					}
				}
			)
		}

		composable<Route.MainScreen> {
			Box(
				modifier = Modifier.fillMaxSize(),
				contentAlignment = Alignment.Center
			) {
				Text("Access Granted ✓")
			}
		}

		composable<Route.BlockedScreen> {
			BlockedScreen()
		}
	}
}