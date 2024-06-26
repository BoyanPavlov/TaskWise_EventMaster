package com.example.taskwise_eventmaster

data class ScreenStrings (val screenName:String, val destinationString: String)

object DestinationStrings{
    val TASK = ScreenStrings("Task Screen","tasks")
    val GOAL = ScreenStrings("Goal Screen","goals")
    val EVENTS = ScreenStrings("Events Screen","events")
    val PLANNING_VIEW = ScreenStrings("Planning-view Screen","planning_view")
    val PROFILE = ScreenStrings("Profile Screen","profile")
    val HOME = ScreenStrings("Home Screen","home")
    val SIGN_IN = ScreenStrings("Sign in Screen","sign_in")
    val DAY_SCREEN = ScreenStrings("Day Screen","day_screen")
}

object DestinationStringArguments{
    val CHOSEN_DATE = "chosenDate"
}