package xyz.teamgravity.coresdkview.navigation

import androidx.annotation.MainThread
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

/**
 * Navigates safely checking if destination exists.
 */
@MainThread
fun NavController.navigateSafely(
    direction: NavDirections,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(direction.actionId) ?: graph.getAction(direction.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(
            resId = direction.actionId,
            args = direction.arguments,
            navOptions = navOptions,
            navigatorExtras = navExtras
        )
    }
}