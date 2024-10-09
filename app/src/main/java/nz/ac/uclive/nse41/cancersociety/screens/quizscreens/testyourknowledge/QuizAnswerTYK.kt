package nz.ac.uclive.nse41.cancersociety.screens.quizscreens.testyourknowledge

import android.net.Uri
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.QuizQuestion
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun QuizAnswerTYKScreen(navController: NavController, currentQuestionIndex: Int, quizCorrect: Boolean, questions: List<QuizQuestion>, currentScore: Int) {


    val quizResponse = questions[currentQuestionIndex].response


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
                            painter = painterResource(R.drawable.men3),
                            contentDescription = "Woman 2",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.women3),
                            contentDescription = "Woman 3",
                            modifier = Modifier.size(250.dp)
                        )
                    }

                    var text = "Next"
                    val nextQuestionIndex = currentQuestionIndex + 1
                    if (nextQuestionIndex == questions.size) {
                        text = "Finish"
                    }

                    Box(modifier = Modifier.fillMaxSize()) {


                            Button(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd),
                                colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),
                                onClick = {


                                val nextQuestionIndex = currentQuestionIndex + 1
                                if (nextQuestionIndex == questions.size) {
                                    //TODO Go to final quiz page saying nice work
                                   // navController.navigate(Screens.MainMenu.route)
                                    navController.navigate("final_score_screen/$currentScore")
                                } else {
                                    val questionsJson = Gson().toJson(questions)
                                    navController.navigate("quizTYK/${Uri.encode(questionsJson)}/$nextQuestionIndex/$currentScore")

                                }
                            }) {
                                Text(text)
                            }
                    }
                }
            }
        }
    }
}
