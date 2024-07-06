package nz.ac.uclive.nse41.cancersociety.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun ScreeningSupportServicesScreen(navController: NavController, fullSequence: Boolean, cancerType: String?) {

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
                text = "Screening Support Services",
                fontSize = responsiveFontSize(),
                fontWeight = FontWeight.Bold
            )

            if (fullSequence) {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (cancerType != null) {

                        Button(
                            onClick = { navController.navigate("${Screens.CancerHomepage.route}/$cancerType") },
                            colors = ButtonDefaults.buttonColors(containerColor = Orange),
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        ) {
                            Text("Home", fontSize = 40.sp, color = Color.Black)
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Orange, shape = MaterialTheme.shapes.small)
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
        }
    }
}