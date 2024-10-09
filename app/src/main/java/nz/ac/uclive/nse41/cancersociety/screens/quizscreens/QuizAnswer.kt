package nz.ac.uclive.nse41.cancersociety.screens.quizscreens

import BackButton
import CustomButton
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.screens.saveLogToFile
import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.utilities.Subsection
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun QuizAnswerScreen(
    navController: NavController,
    nextScreen: String?,
    fullSequence: Boolean,
    cancerType: String?,
    quizResponse: String?,
    quizCorrect: Boolean,
    quizSubsection: String
) {

    val context = LocalContext.current

    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("QuizAnswer", "Time spent: $timeSpent ms")

            saveLogToFile(context, "QuizAnswer", timeSpent, cancerType.toString() + " correct? " + quizCorrect )

        }
    }

    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color.Black
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
                        text = if (quizCorrect) "Correct!" else if (!quizCorrect) "Not quite!" else "blank",
                        fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = quizResponse.toString(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )




                    val imageRes2 = if (cancerType == "Bowel Cancer") {
                        R.drawable.men2
                    } else {
                        R.drawable.women2
                    }
                    val imageRes3 = if (cancerType == "Bowel Cancer") {
                        R.drawable.men3
                    } else {
                        R.drawable.women3
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.women1),
                            contentDescription = "Woman 1",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(imageRes2),
                            contentDescription = "Woman 2",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(imageRes3),
                            contentDescription = "Woman 3",
                            modifier = Modifier.size(250.dp)
                        )
                    }

                    val nextScreenRoute = screenRoutesMap[nextScreen] ?: Screens.MainMenu.route

                    Box(modifier = Modifier.fillMaxSize()) {
                        if (cancerType != null) {

                         /*   if (quizSubsection == "ScreeningSupportServices") {
                                Button(
                                    onClick = { navController.navigate("${Screens.CancerHomepage.route}/$cancerType") },
                                    colors = ButtonDefaults.buttonColors(containerColor = Bluey),
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(16.dp)
                                ) {
                                    Text("Finish", fontSize = 40.sp, color = Color.Black)
                                }
                            }*/
                                CustomButton(
                                    text = "Next",
                                    route = nextScreenRoute,
                                    navController = navController,
                                    fullSequence = fullSequence,
                                    cancerType = cancerType,
                                    enabled = true,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(16.dp)
                                )




                            BackButton(navController)

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
    "ScreeningSupportServices" to Screens.ScreeningSupportServices.route,
    "Final" to Screens.Final.route

)