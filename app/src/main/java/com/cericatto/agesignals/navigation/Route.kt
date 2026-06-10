package com.cericatto.agesignals.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
	@Serializable
	data object CheckAgeScreen : Route

	@Serializable
	data object MainScreen : Route

	@Serializable
	data object BlockedScreen : Route
}