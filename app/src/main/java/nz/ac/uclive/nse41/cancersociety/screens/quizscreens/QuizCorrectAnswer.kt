package nz.ac.uclive.nse41.cancersociety.screens.quizscreens

import CustomButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun QuizCorrectAnswerScreen(navController: NavController, nextScreen: String?, fullSequence: Boolean, cancerType: String?, quizResponse: String?) {

    CancerSocietyTheme(dynamicColor = false) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp),
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

            Text(
                text = "Correct!",
                fontSize = responsiveFontSize(),
                fontWeight = FontWeight.Bold
            )

                    Text(
                        text = quizResponse.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

            val nextScreenRoute = screenRoutesMap[nextScreen] ?: Screens.MainMenu.route

            Box(modifier = Modifier.fillMaxSize()) {
                if (cancerType != null) {
                    CustomButton(
                        text = "Next",
                        route = nextScreenRoute,
                        navController = navController,
                        fullSequence = true,
                        cancerType = cancerType, //TODO CHANGE THIS
                        enabled = true,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    )
                }
            }
        }
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