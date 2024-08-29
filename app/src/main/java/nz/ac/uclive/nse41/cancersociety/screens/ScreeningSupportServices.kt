package nz.ac.uclive.nse41.cancersociety.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.CustomProgressBar
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
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
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                CustomProgressBar(currentScreenIndex = 4)

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


                    screeningSupportServicesSubsection?.info?.get(0)?.let {
                        Text(
                            text = it,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    val infoList = screeningSupportServicesSubsection?.info
                    val bulletPoints = infoList?.drop(1)?.dropLast(1) ?: listOf()


                    bulletPoints.forEach { item ->
                        Text(
                            text = "• $item",  // Adding bullet point before each item
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier.padding(vertical = 4.dp) // Optional padding
                        )
                    }



                    screeningSupportServicesSubsection?.info?.lastOrNull()?.let {
                        Text(
                            text = it,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

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
                                        Text("Finish", fontSize = 40.sp, color = Color.Black)
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



        }
    }
        }
    }
}