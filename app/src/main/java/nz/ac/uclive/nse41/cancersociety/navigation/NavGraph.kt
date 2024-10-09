package nz.ac.uclive.nse41.cancersociety.navigation

import BarriersToGettingScreenedScreen
import MainMenuScreen
import WhatIsScreening
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nz.ac.uclive.nse41.cancersociety.screens.CancerHomepageScreen
import nz.ac.uclive.nse41.cancersociety.screens.FinalScreen
import nz.ac.uclive.nse41.cancersociety.screens.ScreeningSupportServicesScreen
import nz.ac.uclive.nse41.cancersociety.screens.StatisticsScreen
import nz.ac.uclive.nse41.cancersociety.screens.WhereToGetScreenedScreen
import nz.ac.uclive.nse41.cancersociety.screens.WhoCanGetScreenedScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizAnswerScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizCorrectAnswerScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizScreen
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.QuizWrongAnswerScreen

/**
 * Navigation component of the application, which handles navigation between screens and what information to pass between screens
 */
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
            route = "${Screens.WhatIsScreening.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },
                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            WhatIsScreening(navController, fullSequence, cancerType)
        }


        composable(
            route = "${Screens.CancerHomepage.route}/{cancerType}",
            arguments = listOf(navArgument("cancerType") { type = NavType.StringType })
        ) { backStackEntry ->
            val cancerType = backStackEntry.arguments?.getString("cancerType") ?: ""
            CancerHomepageScreen(navController, cancerType)
        }




        composable(
            route = "${Screens.Statistics.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            StatisticsScreen(navController, fullSequence, cancerType)
        }


        composable(
            route = "${Screens.WhoCanGetScreened.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            WhoCanGetScreenedScreen(navController, fullSequence, cancerType)
        }


        composable(
            route = "${Screens.WhereToGetScreened.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            WhereToGetScreenedScreen(navController, fullSequence, cancerType)
        }

        composable(
            route = "${Screens.BarriersToGettingScreened.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            BarriersToGettingScreenedScreen(navController, fullSequence, cancerType)
        }


        composable(
            route = "${Screens.ScreeningSupportServices.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            ScreeningSupportServicesScreen(navController, fullSequence, cancerType)
        }


        composable(
            route = "${Screens.Final.route}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            FinalScreen(navController, fullSequence, cancerType)
        }



        composable(
            route = "${Screens.Quiz.route}/{currentScreen}/{nextScreen}/{fullSequence}/{cancerType}",
            arguments = listOf(
                navArgument("currentScreen") { type = NavType.StringType },
                navArgument("nextScreen") { type = NavType.StringType },
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },
                )
        ) { backStackEntry ->
            val currentScreen = backStackEntry.arguments?.getString("currentScreen")
            val nextScreen = backStackEntry.arguments?.getString("nextScreen")
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")

            QuizScreen(navController, currentScreen, nextScreen, fullSequence, cancerType)
        }

        composable(
            route = "${Screens.QuizAnswer.route}/{nextScreen}/{fullSequence}/{cancerType}/{quizResponse}/{quizCorrect}/{quizSubsection}",
            arguments = listOf(
                navArgument("nextScreen") { type = NavType.StringType },
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },
                navArgument("quizResponse") { type = NavType.StringType },
                navArgument("quizCorrect") { type = NavType.BoolType },
                navArgument("quizSubsection") { type = NavType.StringType },
                )
        ) { backStackEntry ->
            val nextScreen = backStackEntry.arguments?.getString("nextScreen")
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            val quizResponse = backStackEntry.arguments?.getString("quizResponse")
            val quizCorrect = backStackEntry.arguments?.getBoolean("quizCorrect") ?: false
            val quizSubsection = backStackEntry.arguments?.getString("quizSubsection")

            if (quizSubsection != null) {
                QuizAnswerScreen(navController, nextScreen, fullSequence, cancerType, quizResponse, quizCorrect, quizSubsection)
            }
        }


        composable(
            route = "${Screens.QuizCorrectAnswer.route}/{nextScreen}/{fullSequence}/{cancerType}/{quizResponse}",
            arguments = listOf(
                navArgument("nextScreen") { type = NavType.StringType },
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },
                navArgument("quizResponse") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val nextScreen = backStackEntry.arguments?.getString("nextScreen")
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            val quizResponse = backStackEntry.arguments?.getString("quizResponse")


            QuizCorrectAnswerScreen(navController, nextScreen, fullSequence, cancerType, quizResponse)
        }


        composable(
            route = "${Screens.QuizWrongAnswer.route}/{nextScreen}/{fullSequence}/{cancerType}/{quizResponse}",
            arguments = listOf(
                navArgument("nextScreen") { type = NavType.StringType },
                navArgument("fullSequence") { type = NavType.BoolType },
                navArgument("cancerType") { type = NavType.StringType },
                navArgument("quizResponse") { type = NavType.StringType },

                )
        ) { backStackEntry ->
            val nextScreen = backStackEntry.arguments?.getString("nextScreen")
            val fullSequence = backStackEntry.arguments?.getBoolean("fullSequence") ?: false
            val cancerType = backStackEntry.arguments?.getString("cancerType")
            val quizResponse = backStackEntry.arguments?.getString("quizResponse")


            QuizWrongAnswerScreen(navController, nextScreen, fullSequence, cancerType, quizResponse)
        }





    }
}