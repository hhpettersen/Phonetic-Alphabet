package com.example.dinnerplanswitch.ui.navigation

const val DETAIL_ARGUMENT_KEY = "name"
const val DETAIL_ARGUMENT_KEY2 = "surname"
const val DETAIL_ARGUMENT_KEY3 = "city"

private const val DETAIL_SCREEN_ROUTE = "detail_screen"
private const val HOME_SCREEN_ROUTE = "home_screen"
private const val LOGIN_SCREEN_ROUTE = "login_screen"
private const val SIGNUP_SCREEN_ROUTE = "signup_screen"

const val AUTH_ROUTE = "auth"
const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"

sealed class Screen(val route: String) {
    object Home: Screen(route = HOME_SCREEN_ROUTE)
    object Detail: Screen(route = "$DETAIL_SCREEN_ROUTE/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}?={$DETAIL_ARGUMENT_KEY3}") {
        fun passArgs(name: String, surname: String, city: String? = null) =
            "$DETAIL_SCREEN_ROUTE/$name/$surname?=$city"
    }
    object Login: Screen(route = LOGIN_SCREEN_ROUTE)
    object Signup: Screen(route = SIGNUP_SCREEN_ROUTE)
}
