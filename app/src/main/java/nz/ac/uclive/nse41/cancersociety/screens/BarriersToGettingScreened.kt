import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.CustomProgressBar
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.screens.saveLogToFile
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize

@Composable
fun BarriersToGettingScreenedScreen(navController: NavController, fullSequence: Boolean, cancerType: String?) {
    val context = LocalContext.current
    val showAccessDialog = remember { mutableStateOf(false) }
    val showTrustDialog = remember { mutableStateOf(false) }
    val showMindsetDialog = remember { mutableStateOf(false) }


    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("BarriersToGettingScreened", "Time spent: $timeSpent ms")

            saveLogToFile(context, "BarriersToGettingScreened", timeSpent, cancerType.toString())
        }
    }


    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (fullSequence) {
                    if (fullSequence) {
                        CustomProgressBar(
                            currentScreenIndex = 3,
                            modifier = Modifier.align(Alignment.BottomCenter).zIndex(1f)
                        )
                    }                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp),
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Barriers to getting screened",
                        fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = "People may face barriers to getting screened, such as the ones below but there are many support services in place to help and make it easy for you!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.access),
                                contentDescription = "access",
                                modifier = Modifier
                                    .size(350.dp)
                                    .clickable { showAccessDialog.value = true }
                            )
                            Text(
                                text = "Access Issues",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.trust),
                                contentDescription = "trust",
                                modifier = Modifier
                                    .size(350.dp)
                                    .clickable { showTrustDialog.value = true }
                            )
                            Text(
                                text = "Trust Issues",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.mindset),
                                contentDescription = "mindset",
                                modifier = Modifier
                                    .size(350.dp)
                                    .clickable { showMindsetDialog.value = true }
                            )
                            Text(
                                text = "Mindset Issues",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                if (!fullSequence) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (cancerType != null) {
                            CustomButton(
                                text = "Next",
                                route = Screens.ScreeningSupportServices.route,
                                navController = navController,
                                fullSequence = fullSequence,
                                cancerType = cancerType,
                                enabled = true,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(16.dp)
                            )
                        }
                    }
                }

                    Box(modifier = Modifier.fillMaxSize()) {
                        if (cancerType != null) {
                            CustomButton(
                                text = "Next",
                                route = Screens.ScreeningSupportServices.route,
                                navController = navController,
                                fullSequence = fullSequence,
                                cancerType = cancerType,
                                enabled = true,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(16.dp)
                            )
                        }
                    }
                    BackButton(navController)


                if (showAccessDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showAccessDialog.value = false },
                        title = { Text("Access Issues") },
                        text = { Text("Travel time to GP, hard to get appointments") },
                        confirmButton = {
                            Button(onClick = { showAccessDialog.value = false }) {
                                Text("Close")
                            }
                        }
                    )
                }

                if (showTrustDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showTrustDialog.value = false },
                        title = { Text("Trust Issues") },
                        text = { Text("Shame/shyness of discussing body parts") },
                        confirmButton = {
                            Button(onClick = { showTrustDialog.value = false }) {
                                Text("Close")
                            }
                        }
                    )
                }

                if (showMindsetDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showMindsetDialog.value = false },
                        title = { Text("Mindset Issues") },
                        text = { Text("Don't want to know, 'she'll be right'") },
                        confirmButton = {
                            Button(onClick = { showMindsetDialog.value = false }) {
                                Text("Close")
                            }
                        }
                    )
                }
            }
        }
    }
}
