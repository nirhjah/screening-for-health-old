package nz.ac.uclive.nse41.cancersociety.screens

import android.annotation.SuppressLint

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MainMenuScreen(navController: NavController) {

    CancerSocietyTheme(dynamicColor = false) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Screening for Health", fontSize = 60.sp, modifier = Modifier
                    .padding(50.dp) )


                NavButton(
                    text = "Breast Cancer",
                    navController = navController
                )

                NavButton(
                    text = "Bowel Cancer",
                    navController = navController
                )

                NavButton(
                    text = "Cervical Cancer",
                    navController = navController
                )


            }


        }



    }

    BackHandler {
    }
}

@Composable
fun NavButton(
    text: String,
    navController: NavController,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Orange),
    modifier: Modifier = Modifier.height(150.dp).width(400.dp)
) {
    Button(
        onClick = { navController.navigate("${Screens.CancerHomepage.route}/$text") },
        colors = colors,
        modifier = modifier.semantics { testTag = "text1" }
    ) {
        Text(text, fontSize = 40.sp, color = Color.Black)
    }
}



@Composable
fun CustomButton(
    text: String,
    route: String,
    navController: NavController,
    fullSequence: Boolean,
    cancerType: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Orange),
    modifier: Modifier = Modifier.height(150.dp).width(400.dp)
) {
    Button(
        onClick = { navController.navigate("$route/$fullSequence/$cancerType") },
        colors = colors,
        modifier = modifier
    ) {
        Text(text, fontSize = 40.sp, color = Color.Black)
    }
}
