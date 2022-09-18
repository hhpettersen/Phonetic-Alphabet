package com.example.dinnerplanswitch.ui.navigation

const val DETAIL_ARGUMENT_KEY = "name"
const val DETAIL_ARGUMENT_KEY2 = "surname"
const val DETAIL_ARGUMENT_KEY3 = "city"

private const val DETAIL_SCREEN_ROUTE = "detail_screen"
private const val HOME_SCREEN_ROUTE = "home_screen"

sealed class Screen(val route: String) {
    object Home: Screen(route = HOME_SCREEN_ROUTE)
    object Detail: Screen(route = "$DETAIL_SCREEN_ROUTE/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}?={$DETAIL_ARGUMENT_KEY3}") {
        fun passArgs(name: String, surname: String, city: String? = null) =
            "$DETAIL_SCREEN_ROUTE/$name/$surname?=$city"
    }
}
