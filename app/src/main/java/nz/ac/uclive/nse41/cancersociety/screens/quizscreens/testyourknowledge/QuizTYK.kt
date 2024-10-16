package nz.ac.uclive.nse41.cancersociety.screens.quizscreens.testyourknowledge


import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson

import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.QuizQuestion
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@SuppressLint("SuspiciousIndentation")
@Composable
fun QuizTYKScreen(navController: NavController, questions: List<QuizQuestion>, currentQuestionIndex: Int, currentScore: Int) {
    val quizQuestion = questions[currentQuestionIndex]

    val selectedAnswer = remember { mutableStateOf<String?>(null) }



    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color.Black
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                val currQuestionDisplay = currentQuestionIndex + 1
                Text(text = "Question $currQuestionDisplay / 5", fontSize = 19.sp, modifier = Modifier.align(
                    Alignment.TopStart))

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = quizQuestion.question,
                        fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        lineHeight = 49.sp
                    )


                }


                val answers = quizQuestion.answers
                if (answers.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.4f)
                            .padding(16.dp)
                            .aspectRatio(1f)
                            .align(Alignment.Center)
                    ) {
                        MyGridTYK(answers, selectedAnswer)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {


                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),
                        enabled = selectedAnswer.value != null,
                        onClick = {
                        val quizCorrect = selectedAnswer.value == quizQuestion.correctAnswer
                            val updatedScore = if (quizCorrect) currentScore + 1 else currentScore

                        val questionsJson = Gson().toJson(questions)
                            navController.navigate("quiz_answer_tyk/$currentQuestionIndex/$quizCorrect/${Uri.encode(questionsJson)}/$updatedScore")

                    }) {
                        Text("Check")
                    }
                }
            }
        }
    }
}







@Composable
fun MyGridTYK(answers: List<String>, selectedAnswer: MutableState<String?>) {
    val selectedCard = remember { mutableStateOf<Int?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(answers) { answer ->
            MyCardTYK(text = answer, selectedAnswer = selectedAnswer)

        }
    }
}

@Composable
fun MyCardTYK(text: String, selectedAnswer: MutableState<String?>) {
    val isSelected = remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (selectedAnswer.value == text)  Bluey else Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clickable { selectedAnswer.value = text },
        elevation = if (isSelected.value) CardDefaults.cardElevation(defaultElevation = 6.dp) else CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            ) }
    }

}
