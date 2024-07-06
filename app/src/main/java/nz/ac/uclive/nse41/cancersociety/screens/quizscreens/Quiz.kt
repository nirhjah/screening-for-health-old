package nz.ac.uclive.nse41.cancersociety.screens.quizscreens

import android.util.Log
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
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.screens.CustomButton
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun QuizScreen(navController: NavController, nextScreen: String?, fullSequence: Boolean, cancerType: String?) {

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
                text = "Quiz Question",
                fontSize = responsiveFontSize(),
                fontWeight = FontWeight.Bold
            )




            Box(modifier = Modifier.fillMaxSize()) {
                if (cancerType != null) {
                    CustomButton(
                        text = "Check",
                        route = "${Screens.QuizCorrectAnswer.route}/$nextScreen",
                        navController = navController,
                        fullSequence = true,
                        cancerType = cancerType, //TODO CHANGE THIS
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