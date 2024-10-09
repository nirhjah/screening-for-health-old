package nz.ac.uclive.nse41.cancersociety.navigation

import androidx.annotation.StringRes
import nz.ac.uclive.nse41.cancersociety.R

/**
 * Defines all Screens within the app, for navigation purposes.
 */
sealed class Screens(val route: String, @StringRes val resourceId: Int) {
    object MainMenu : Screens("main_menu", R.string.main_screen)
    object CancerHomepage : Screens("cancer_homepage", R.string.cancer_homepage)
    object WhatIsScreening : Screens("what_is_screening", R.string.what_is_screening)
    object Statistics : Screens("statistics", R.string.statistics)
    object WhoCanGetScreened : Screens("who_can_get_screened", R.string.who_can_get_screened)
    object Symptoms : Screens("symptoms", R.string.symptoms)
    object WhereToGetScreened : Screens("where_to_get_screened", R.string.where_to_get_screened)
    object BarriersToGettingScreened : Screens("barriers_to_getting_screened", R.string.barriers_to_getting_screened)
    object ScreeningSupportServices : Screens("screening_support_services", R.string.screening_support_services)
    object Final : Screens("final", R.string.final_screen)
    object Quiz : Screens("quiz", R.string.quiz)
    object QuizCorrectAnswer : Screens("quiz_correct_answer", R.string.quiz_correct_answer)
    object QuizWrongAnswer : Screens("quiz_wrong_answer", R.string.quiz_wrong_answer)
    object QuizAnswer : Screens("quiz_answer", R.string.quiz_answer)


}