package nz.ac.uclive.nse41.cancersociety.navigation

import WhatIsScreening
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nz.ac.uclive.nse41.cancersociety.MainMenuScreen
import nz.ac.uclive.nse41.cancersociety.screens.BarriersToGettingScreenedScreen
import nz.ac.uclive.nse41.cancersociety.screens.CancerHomepageScreen
import nz.ac.uclive.nse41.cancersociety.screens.ScreeningSupportServicesScreen
import nz.ac.uclive.nse41.cancersociety.screens.StatisticsScreen
import nz.ac.uclive.nse41.cancersociety.screens.SymptomsScreen
import nz.ac.uclive.nse41.cancersociety.screens.WhereToGetScreenedScreen
import nz.ac.uclive.nse41.cancersociety.screens.WhoCanGetScreenedScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizCorrectAnswerScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizWrongAnswerScreen

@Composable
fun NavGraph (navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.MainMenu.route
    ) {
        composable(
            route = Screens.MainMenu.route
        ) { backStackEntry ->
            MainMenuScreen(navController)
        }

        composable(
            route = Screens.WhatIsScreening.route
        ) {backStackEntry ->
            WhatIsScreening(navController)
        }


      /*  composable(
            route = Screens.CancerHomepage.route
        ) {backStackEntry ->
            CancerHomepageScreen(navController)
        }
*/

        composable(
            route = "${Screens.CancerHomepage.route}/{cancerType}",
            arguments = listOf(navArgument("cancerType") { type = NavType.StringType })
        ) { backStackEntry ->
            val cancerType = backStackEntry.arguments?.getString("cancerType") ?: ""
            CancerHomepageScreen(navController, cancerType)
        }

        composable(
            route = Screens.Statistics.route
        ) {backStackEntry ->
            StatisticsScreen(navController)
        }


        composable(
            route = Screens.WhoCanGetScreened.route
        ) {backStackEntry ->
            WhoCanGetScreenedScreen(navController)
        }


        composable(
            route = Screens.Symptoms.route
        ) {backStackEntry ->
            SymptomsScreen(navController)
        }


        composable(
            route = Screens.WhereToGetScreened.route
        ) {backStackEntry ->
            WhereToGetScreenedScreen(navController)
        }


        composable(
            route = Screens.BarriersToGettingScreened.route
        ) {backStackEntry ->
            BarriersToGettingScreenedScreen(navController)
        }


        composable(
            route = Screens.ScreeningSupportServices.route
        ) {backStackEntry ->
            ScreeningSupportServicesScreen(navController)
        }

        composable(
            route = "${Screens.Quiz.route}/{nextScreen}",
            arguments = listOf(navArgument("nextScreen") { type = NavType.StringType })
        ) { backStackEntry ->
            val nextScreen = backStackEntry.arguments?.getString("nextScreen")
            QuizScreen(navController, nextScreen)
        }


        composable(
            route = "${Screens.QuizCorrectAnswer.route}/{nextScreen}",
            arguments = listOf(navArgument("nextScreen") { type = NavType.StringType })
        ) { backStackEntry ->
            val nextScreen = backStackEntry.arguments?.getString("nextScreen")
            QuizCorrectAnswerScreen(navController, nextScreen)
        }

        composable(
            route = Screens.QuizWrongAnswer.route
        ) {backStackEntry ->
            QuizWrongAnswerScreen(navController)
        }






    }
}