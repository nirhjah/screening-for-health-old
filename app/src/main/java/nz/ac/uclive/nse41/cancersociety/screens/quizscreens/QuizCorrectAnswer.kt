package nz.ac.uclive.nse41.cancersociety.screens.quizscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.CustomButton
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme

@Composable
fun QuizCorrectAnswerScreen(navController: NavController, nextScreen: String?) {

    CancerSocietyTheme(dynamicColor = false) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {

            Text("Correct")
            Text("next screen: $nextScreen")

            val nextScreenRoute = screenRoutesMap[nextScreen] ?: Screens.MainMenu.route

            Box(modifier = Modifier.fillMaxSize()) {
                CustomButton(
                    text = "Next",
                    route = nextScreenRoute,
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }
        }
    }
}

val screenRoutesMap = mapOf(
    "Symptoms" to Screens.Symptoms.route,
    "WhoCanGetScreened" to Screens.WhoCanGetScreened.route,
    "WhereToGetScreened" to Screens.WhereToGetScreened.route,
    "BarriersToGettingScreened" to Screens.BarriersToGettingScreened.route,
    "ScreeningSupportServices" to Screens.ScreeningSupportServices.route

)
