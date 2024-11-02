package nz.ac.uclive.nse41.cancersociety

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import nz.ac.uclive.nse41.cancersociety.navigation.NavGraph
import nz.ac.uclive.nse41.cancersociety.screens.quizscreens.testyourknowledge.QuizTYKScreen
import nz.ac.uclive.nse41.cancersociety.utilities.QuizQuestion
import org.junit.Rule
import org.junit.Test

class AppFlowTest {

    @get:Rule
    val rule = createComposeRule()


    @Test
    fun clickBreastCancerOnMainMenu_goToBreastCancerHomepage() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }

        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Breast Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithTag("cancerHomepageTitle").assertTextEquals("Breast Cancer")
    }

    @Test
    fun clickBowelCancerOnMainMenu_goToBowelCancerHomepage() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }

        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Bowel Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithTag("cancerHomepageTitle").assertTextEquals("Bowel Cancer")
    }

    @Test
    fun clickCervicalCancerOnMainMenu_goToCervicalCancerHomepage() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }

        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Cervical Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithTag("cancerHomepageTitle").assertTextEquals("Cervical Cancer")
    }



    @Test
    fun clickLearnThemAll_GoToWhatIsScreening_WithNextButton() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }

        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Cervical Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Or learn them all!").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithContentDescription("hospital").assertIsDisplayed()
        rule.onNodeWithText("Next").assertIsDisplayed()

    }

    @Test
    fun clickWhatIsScreening_GoToWhatIsScreening_WithoutNextButton() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }


        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Cervical Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("What is screening?").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithContentDescription("hospital").assertIsDisplayed()
        rule.onNodeWithText("Next").assertIsNotDisplayed()


    }

    @Test
    fun clickTestYourKnowledge_GoToQuiz() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }

        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Cervical Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Test your knowledge of Cervical Cancer in NZ").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Check").assertIsDisplayed()

    }

    @Test
    fun navigateToQuizAnswerScreen_whenCheckButtonClicked_CorrectAnswer() {
        val question = QuizQuestion(
            question = "Test question",
            answers = listOf("Wrong", "CorrectAnswer", "Wrong2", "Wrong3"),
            correctAnswer = "CorrectAnswer",
            subsection = "WhoCanGetScreened",
            response = ""
        )
        val questions = listOf(question)
        rule.setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)
            QuizTYKScreen(
                navController = navController,
                questions = questions,
                currentQuestionIndex = 0,
                currentScore = 0
            )
        }

        rule.onNodeWithText("CorrectAnswer").performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Check").performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Correct!").assertIsDisplayed()

    }

    @Test
    fun navigateToQuizAnswerScreen_whenCheckButtonClicked_WrongAnswer() {
        val question = QuizQuestion(
            question = "Test question",
            answers = listOf("Wrong", "CorrectAnswer", "Wrong2", "Wrong3"),
            correctAnswer = "CorrectAnswer",
            subsection = "WhoCanGetScreened",
            response = ""
        )
        val questions = listOf(question)
        rule.setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)
            QuizTYKScreen(
                navController = navController,
                questions = questions,
                currentQuestionIndex = 0,
                currentScore = 0
            )
        }

        rule.onNodeWithText("Wrong2").performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Check").performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Not quite!").assertIsDisplayed()

    }


    @Test
    fun clickWhereToGetScreenedBowel_GoToWhereToGetScreened_WithMailbox() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }


        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Bowel Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Where to get screened?").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithContentDescription("mailbox").assertIsDisplayed()


    }


    @Test
    fun clickWhereToGetScreenedBreast_GoToWhereToGetScreened_WithVan() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }


        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Breast Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Where to get screened?").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithContentDescription("van").assertIsDisplayed()


    }

    @Test
    fun clickWhereToGetScreenedCervical_GoToWhereToGetScreened_WithDoctor() {
        rule.setContent { val navController = rememberNavController()
            NavGraph(navController = navController)
        }


        rule.onNodeWithText("Screening for Health").assertIsDisplayed()
        rule.onNodeWithText("Cervical Cancer").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithText("Where to get screened?").assertIsDisplayed().performClick()
        rule.waitForIdle()
        rule.onNodeWithContentDescription("doctor").assertIsDisplayed()


    }

}