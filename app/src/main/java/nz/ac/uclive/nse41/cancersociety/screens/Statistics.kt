package nz.ac.uclive.nse41.cancersociety.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.getCancerInfoFromJson

@Composable
fun StatisticsScreen(navController: NavController, fullSequence: Boolean, cancerType: String?) {
    val context = LocalContext.current
    val cancerInfo = getCancerInfoFromJson(context, "CancerInfo.json")
    val selectedCancer = cancerInfo?.cancers?.find { it.cancer == cancerType }
    val statisticsSubsection = selectedCancer?.subsections?.find { it.subsection == "Statistics" }


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
                    Text("Statistics")

                    if (statisticsSubsection != null) {
                        statisticsSubsection.info.forEach { string ->
                            Text(text = string)
                        }
                    }
                }

                if (fullSequence) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (cancerType != null) {
                            CustomButton(
                                text = "Next",
                                route = Screens.WhoCanGetScreened.route,
                                navController = navController,
                                fullSequence = fullSequence,
                                cancerType = cancerType,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(16.dp)
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Orange,
                                shape = MaterialTheme.shapes.small
                            )
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