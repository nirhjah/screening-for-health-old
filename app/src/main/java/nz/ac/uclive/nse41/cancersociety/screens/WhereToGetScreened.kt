package nz.ac.uclive.nse41.cancersociety.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme

@Composable
fun WhereToGetScreenedScreen(navController: NavController) {

    CancerSocietyTheme(dynamicColor = false) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {

            Text("Where to get screened")

        /*    Box(modifier = Modifier.fillMaxSize()) {
                CustomButton(
                    text = "Next",
                    route = Screens.BarriersToGettingScreened.route,
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }*/

        }
    }
}