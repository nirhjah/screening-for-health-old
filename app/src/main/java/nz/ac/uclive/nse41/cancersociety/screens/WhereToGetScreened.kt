package nz.ac.uclive.nse41.cancersociety.screens

import CustomButton
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import nz.ac.uclive.nse41.cancersociety.CustomProgressBar
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.getCancerInfoFromJson
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveHospitalImage

@Composable
fun WhereToGetScreenedScreen(navController: NavController, fullSequence: Boolean, cancerType: String?) {
    var isVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val cancerInfo = getCancerInfoFromJson(context, "CancerInfo.json")
    val selectedCancer = cancerInfo?.cancers?.find { it.cancer == cancerType }
    val whereToGetScreenedSubSection = selectedCancer?.subsections?.find { it.subsection == "Where to get screened" }

    val targetOffsetX: Dp = 200.dp
    val initialOffsetX: Dp = (-300).dp
    val offsetX = remember { Animatable(initialOffsetX.value) }
    var showCard by remember { mutableStateOf(false) }


    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("WhereToGetScreenedScreen", "Time spent: $timeSpent ms")

            saveLogToFile(context, "WhereToGetScreenedScreen", timeSpent, cancerType.toString())
        }
    }


    LaunchedEffect(Unit) {
        delay(500)
        offsetX.animateTo(
            targetValue = targetOffsetX.value,
            animationSpec = tween(durationMillis = 3000)
        )
        delay(500)
        isVisible = true
        showCard = true
    }

    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                CustomProgressBar(currentScreenIndex = 2)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp),
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Where to get screened",
                        fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold
                    )

                }

                Spacer(modifier = Modifier.height(400.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(120.dp)
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        items(5) {
                            Image(
                                painter = painterResource(id = R.drawable.road),
                                contentDescription = "road",
                                modifier = Modifier.size(290.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }


                val imageRes1 = if (cancerType == "Bowel Cancer") {
                    R.drawable.men3
                } else {
                    R.drawable.women3
                }


                Image(
                    painter = painterResource(id = imageRes1),
                    contentDescription = "woman1",
                    modifier = Modifier
                        .size(350.dp)
                        .offset(x = Dp(offsetX.value), y = 280.dp)
                )

                if (cancerType == "Bowel Cancer") {
                    Image(
                        painter = painterResource(id = R.drawable.house),
                        contentDescription = "house",
                        modifier = Modifier
                            .size(responsiveHospitalImage())
                            .align(Alignment.Center)
                            .offset(x = 0.dp, y = 90.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.hospital),
                        contentDescription = "hospital",
                        modifier = Modifier
                            .size(responsiveHospitalImage())
                            .align(Alignment.Center)
                            .offset(x = 0.dp, y = 70.dp)
                    )
                }

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + slideInHorizontally(
                        animationSpec = tween(durationMillis = 3000),
                        initialOffsetX = { 700 }
                        ),

                    modifier = Modifier.offset(900.dp, 260.dp)

                ) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 6.dp)


/*
                            .offset(x = responsiveHospitalImage() + 516.dp, y = 260.dp)
*/
                            .size(300.dp, 300.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            whereToGetScreenedSubSection?.info?.get(0)?.let {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Button(
                                onClick = {

                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whereToGetScreenedSubSection?.info?.get(1)))
                                    context.startActivity(intent)

                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Black),

                            ) {
                                Text("Click to find your closest clinic!")
                            }
                        }
                    }

                }

                if (fullSequence) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (cancerType != null) {
                            CustomButton(
                                text = "Next",
                                route = "${Screens.Quiz.route}/WhereToGetScreened/BarriersToGettingScreened",
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
                } else {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Orange, shape = MaterialTheme.shapes.small)
                            .align(Alignment.BottomStart)
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
