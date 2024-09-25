
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.screens.saveLogToFile
import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun MainMenuScreen(navController: NavController) {

    val context = LocalContext.current

    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("MainMenu", "Time spent: $timeSpent ms")

            saveLogToFile(context, "MainMenu", timeSpent, "")
        }
    }


    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image of women1 on the left
                Image(
                    painter = painterResource(id = R.drawable.women1),
                    contentDescription = "Women1",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(end = 16.dp)
                )

                // Column of buttons
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Screening for Health", fontSize = responsiveFontSize(), fontWeight = FontWeight.Bold, modifier = Modifier
                            .padding(0.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {

                        NavButton(
                            text = "Breast Cancer",
                            cancerType = "Breast Cancer",
                            navController = navController
                        )

                        NavButton(
                            text = "Bowel Cancer",
                            cancerType = "Bowel Cancer",
                            navController = navController
                        )


                        NavButton(
                            text = "Cervical Cancer",
                            cancerType = "Cervical Cancer",
                            navController = navController
                        )

                    }

                }

                // Image of women2 on the right
                Image(
                    painter = painterResource(id = R.drawable.men2),
                    contentDescription = "Women2",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(start = 16.dp)
                )
            }

        }
    }
}


@Composable
fun NavButton(
    text: String,
    cancerType: String,
    navController: NavController,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Orange),
    modifier: Modifier = Modifier
        .height(120.dp) //150.dp
        .width(400.dp)
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Orange,
            contentColor = Color.Black),
        modifier = Modifier
            .size(width = 200.dp, height = 200.dp)
            .clickable { navController.navigate("${Screens.CancerHomepage.route}/$cancerType") }


    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = text,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 30.sp
                    )
                )

            }
        }
    }

}

/*@Composable
fun NavButton(
    text: String,
    cancerType: String,
    navController: NavController,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Orange),
    modifier: Modifier = Modifier
        .height(120.dp) //150.dp
        .width(400.dp)
) {
    Button(
        onClick = { navController.navigate("${Screens.CancerHomepage.route}/$cancerType") },
        colors = colors,
        modifier = modifier.semantics { testTag = "text1" }
    ) {
        Text(text, fontSize = 40.sp, color = Color.Black)
    }
}*/


@Composable
fun BackButton(navController: NavController) {
    Button(
        onClick = { navController.popBackStack() },
        colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),
        modifier = Modifier
            .height(60.dp)
            .width(150.dp)
            .background(Bluey, shape = MaterialTheme.shapes.large)
    ) {
        Text(text = "Back", fontSize = 40.sp)
    }
}

@Composable
fun HomepageBackButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(Screens.MainMenu.route) },
        colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),
        modifier = Modifier
            .height(60.dp)
            .width(150.dp)
            .background(Bluey, shape = MaterialTheme.shapes.large)
    ) {
        Text(text = "Back", fontSize = 40.sp)
    }
}


@Composable
fun CustomButton(
    text: String,
    route: String,
    navController: NavController,
    fullSequence: Boolean,
    cancerType: String,
    enabled: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Bluey),
    modifier: Modifier = Modifier
        .height(150.dp)
        .width(400.dp)
) {
    val backgroundColor = if (enabled) Bluey else Color.Gray
    val contentColor = if (enabled) Color.Black else Color.DarkGray

    Button(
        onClick = {
            if (enabled) {
                Log.d("CustomButton", "Navigating to $route/$fullSequence/$cancerType")
                navController.navigate("$route/$fullSequence/$cancerType")
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = contentColor),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text, fontSize = 40.sp, color = contentColor)
    }
}

@Composable
fun QuizCheckButton(
    text: String,
    route: String,
    navController: NavController,
    fullSequence: Boolean,
    cancerType: String,
    quizResponse: String,
    enabled: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Bluey),
    modifier: Modifier = Modifier
        .height(150.dp)
        .width(400.dp)
) {
    val backgroundColor = if (enabled) Bluey else Color.Gray
    val contentColor = if (enabled) Color.Black else Color.DarkGray

    Button(
        onClick = {
            if (enabled) {
                Log.d("CustomButton", "Navigating to $route/$fullSequence/$cancerType/$quizResponse")
                navController.navigate(route)
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = contentColor),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text, fontSize = 40.sp, color = contentColor)
    }
}
