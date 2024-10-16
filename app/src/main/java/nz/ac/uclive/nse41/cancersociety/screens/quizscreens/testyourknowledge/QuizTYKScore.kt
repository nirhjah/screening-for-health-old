package nz.ac.uclive.nse41.cancersociety.screens.quizscreens.testyourknowledge

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
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun FinalScoreScreen(navController: NavController, finalScore: Int) {


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
                    Text(text = "Quiz finished!",
                            fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold)

                    Text(text = "You scored $finalScore out of 5 questions!",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold)


                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),
                        onClick = {
                            navController.navigate(Screens.MainMenu.route)
                        }
                    ) {
                        Text("Main Menu")
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
                            painter = painterResource(id = R.drawable.women4),
                            contentDescription = "Woman 2",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.men1),
                            contentDescription = "Woman 3",
                            modifier = Modifier.size(250.dp)
                        )
                    }




                }

            }

        }

    }

}