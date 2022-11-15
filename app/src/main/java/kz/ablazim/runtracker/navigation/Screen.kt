package kz.ablazim.runtracker.navigation

sealed class Screen(val route: String) {
    object SignUp: Screen(SIGN_UP_ROUTE)
    object SignIn: Screen(SIGN_IN_ROUTE)
    object Home: Screen(HOME_ROUTE)

    private companion object {
        const val SIGN_UP_ROUTE = "signup"
        const val SIGN_IN_ROUTE = "signin"
        const val HOME_ROUTE = "home"
    }
}