package nz.ac.uclive.nse41.cancersociety.screens

import BackButton
import CustomButton
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.CustomProgressBar
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.getCancerInfoFromJson
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun ScreeningSupportServicesScreen(navController: NavController, fullSequence: Boolean, cancerType: String?) {
    val context = LocalContext.current
    val cancerInfo = getCancerInfoFromJson(context, "CancerInfo.json")
    val selectedCancer = cancerInfo?.cancers?.find { it.cancer == cancerType }

    val screeningSupportServicesSubsection = selectedCancer?.subsections?.find { it.subsection == "Screening support services" }
    Log.d("screeningSupportServicesSubsection", screeningSupportServicesSubsection.toString())

    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("ScreeningSupportServicesScreen", "Time spent: $timeSpent ms")
            saveLogToFile(context, "ScreeningSupportServicesScreen", timeSpent, cancerType.toString())
        }
    }

    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color.Black
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                BackButton(navController)

                if (fullSequence) {
                    CustomProgressBar(
                        currentScreenIndex = 4,
                        modifier = Modifier.align(Alignment.BottomCenter).zIndex(1f)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Screening Support Services",
                        fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold
                    )

                    // Display information text
                    screeningSupportServicesSubsection?.info?.get(0)?.let {
                        Text(
                            text = it,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Bullet points
                    val bulletPoints = screeningSupportServicesSubsection?.info?.drop(1)?.dropLast(1) ?: listOf()
                    bulletPoints.forEach { item ->
                        Text(
                            text = "• $item",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }

                    // Last info text
                    screeningSupportServicesSubsection?.info?.lastOrNull()?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Button for Screening Support Services - centered above the images
                    Button(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://info.health.nz/keeping-healthy/cancer-screening/screening-support-services-in-aotearoa-new-zealand#AccordionItems-5307")
                            )
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Bluey,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Click to find your nearest screening support services!")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Images row - centered at the bottom
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {


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

                        Image(
                            painter = painterResource(id = R.drawable.women1),
                            contentDescription = "Woman 1",
                            modifier = Modifier.size(200.dp)
                        )
                        Image(
                            painter = painterResource(imageRes2),
                            contentDescription = "Woman 2",
                            modifier = Modifier.size(200.dp)
                        )
                        Image(
                            painter = painterResource(imageRes3),
                            contentDescription = "Woman 3",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }



                if (fullSequence) {

                    if (cancerType != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.BottomCenter),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End

                        ) {

                            CustomButton(
                                text = "Next",
                                route = "${Screens.Quiz.route}/ScreeningSupportServices/WhereToGetScreened",
                                navController = navController,
                                fullSequence = fullSequence,
                                cancerType = cancerType,
                                enabled = true,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }} else {
                        //not full
                    Button(
                        onClick = { navController.navigate("${Screens.CancerHomepage.route}/$cancerType") },
                        colors = ButtonDefaults.buttonColors(containerColor = Bluey),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        Text("Finish", fontSize = 40.sp, color = Color.Black)
                    }
                }






            }
        }
    }
}
