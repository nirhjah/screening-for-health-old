import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import nz.ac.uclive.nse41.cancersociety.CustomProgressBar
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.screens.saveLogToFile
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveHospitalImage

@Composable
fun WhatIsScreening(navController: NavController, fullSequence: Boolean, cancerType: String?) {
    val context = LocalContext.current


    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("WhatIsScreening", "Time spent: $timeSpent ms")

            saveLogToFile(context, "WhatIsScreening", timeSpent, cancerType.toString())
        }
    }

    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                val configuration = LocalConfiguration.current
                val orientation = configuration.orientation
                Log.d("Orientation", "The orientation is: " + orientation)

                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (cancerType != null) {
                        AnimatedImageSlide(portrait = true, cancerType = cancerType)
                    }
                } else {
                    if (cancerType != null) {
                        AnimatedImageSlide(portrait = false, cancerType = cancerType)
                    }
                }




               if (fullSequence) {
                   Box(modifier = Modifier.fillMaxSize()) {
                       if (cancerType != null) {
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(16.dp)
                                   .align(Alignment.BottomCenter),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               // Place progress bar on the left
                               CustomProgressBar(currentScreenIndex = 0)

                               // Place "Next" button on the right
                               CustomButton(
                                   text = "Next",
                                   route = Screens.WhoCanGetScreened.route,
                                   navController = navController,
                                   fullSequence = fullSequence,
                                   cancerType = cancerType,
                                   enabled = true,
                                   modifier = Modifier
                                       .align(Alignment.CenterVertically)
                               )
                           }
                       }
                   }
               } else {
                   Box(
                       modifier = Modifier
                           .size(48.dp)
                           .background(Orange, shape = MaterialTheme.shapes.small)
                           .align(Alignment.BottomStart)
                   ) {
                       IconButton(
                           onClick = { navController.popBackStack() }) {
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


@Composable
fun ScreeningInfoColumn(
    fontSize: TextUnit,
    hospitalSize: Dp,
    alpha: Float,
    textToShow: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What is Screening?",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.hospital),
            contentDescription = "hospital",
            modifier = Modifier.size(hospitalSize) //350
        )

        Text(
            text = stringResource(textToShow),
            fontSize = 30.sp,
            modifier = Modifier.graphicsLayer(alpha = alpha),
            fontWeight = FontWeight.Bold
        )
    }
}




@Composable
fun AnimatedImageSlide(portrait: Boolean, cancerType: String) {

    var startAnimation by remember { mutableStateOf(false) }
    var textToShow by remember { mutableStateOf(R.string.even_if_you_feel_well) }
    var textAlpha by remember { mutableStateOf(1f) }

    val alpha by animateFloatAsState(
        targetValue = textAlpha,
        animationSpec = tween(durationMillis = 1000)
    )

    if (!portrait) {
        Log.d("portrait or not:", portrait.toString() + " should NOT be ")


        var women2Offset by remember { mutableStateOf(110.dp) }
        var women3Offset by remember { mutableStateOf(220.dp) }
        var women1Offset by remember { mutableStateOf(0.dp) }
        val offsetX by animateDpAsState(
            targetValue = if (startAnimation) 0.dp else (-550).dp,
            animationSpec = tween(durationMillis = 4000)
        )





        LaunchedEffect(Unit) {
            delay(1000)
            startAnimation = true
            delay(3000)
            textAlpha = 0f
            delay(1400)
            textToShow = R.string.screening_will_help
            textAlpha = 1f
            delay(1300)
            women2Offset += 300.dp //200
            women3Offset += 300.dp //200
            delay(2400)
            women1Offset += 295.dp
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            ScreeningInfoColumn(
                fontSize = responsiveFontSize(),
                hospitalSize = responsiveHospitalImage(),
                alpha = alpha,
                textToShow = textToShow

            )

            val women1OffsetAnimated by animateDpAsState(
                targetValue = women1Offset,
                animationSpec = tween(durationMillis = 4000)
            )

          /*  Image(
                painter = painterResource(id = R.drawable.women1),
                contentDescription = "women1",
                modifier = Modifier
                    .size(350.dp) //350
                    .offset(x = offsetX, y = 0.dp)
            )*/

            val imageRes1 = if (cancerType == "Bowel Cancer") {
                R.drawable.men1
            } else {
                R.drawable.women1
            }

            Image(
                painter = painterResource(id = imageRes1
                ),
                contentDescription = "women1",
                modifier = Modifier
                    .size(350.dp) //350
                    .offset(x = offsetX + women1OffsetAnimated)
            )

            // Animation for women2
            val women2OffsetAnimated by animateDpAsState(
                targetValue = if (startAnimation) women2Offset else (women2Offset),
                animationSpec = tween(durationMillis = 4000)
            )

            val women3OffsetAnimated by animateDpAsState(
                targetValue = if (startAnimation) women3Offset else (women3Offset),
                animationSpec = tween(durationMillis = 4000)
            )

            val imageRes2 = if (cancerType == "Bowel Cancer") {
                R.drawable.men2
            } else {
                R.drawable.women2
            }

            Image(
                painter = painterResource(id = imageRes2),
                contentDescription = "women2",
                modifier = Modifier
                    .size(350.dp) //350
                    .offset(x = offsetX + women2OffsetAnimated)
            )

            // Animation for women3


            Image(
                painter = painterResource(id = R.drawable.women3),
                contentDescription = "women3",
                modifier = Modifier
                    .size(350.dp) //350
                    .offset(x = offsetX + women3OffsetAnimated)
            )
        }




    } else {
        Log.d("Orientation", portrait.toString() + " should be portrait")

        var women1Offsety by remember { mutableStateOf(-250.dp) }

        var women2Offsetx by remember { mutableStateOf(70.dp) } //110
        var women2Offsety by remember { mutableStateOf(-250.dp) }

        var women3Offsetx by remember { mutableStateOf(160.dp) } //220
        var women3Offsety by remember { mutableStateOf(-250.dp) }

        val offsetX by animateDpAsState(
            targetValue = if (startAnimation) (-70).dp else (-150).dp,
            animationSpec = tween(durationMillis = 4000)
        )


        LaunchedEffect(Unit) {
            delay(1000)
            startAnimation = true
            delay(3000)
            textAlpha = 0f
            delay(1200)
            textToShow = R.string.screening_will_help
            textAlpha = 1f

            delay(1300)
            women2Offsety += 390.dp //200
            women3Offsety += 390.dp //200


        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            ScreeningInfoColumn(
                fontSize = responsiveFontSize(),
                hospitalSize = responsiveHospitalImage(),
                alpha = alpha,
                textToShow = textToShow
            )

            Image(
                painter = painterResource(id = R.drawable.women1),
                contentDescription = "women1",
                modifier = Modifier
                    .size(300.dp) //350
                    .offset(x = offsetX, y = women1Offsety)
            )

            // Animation for women2
            val women2OffsetxAnimated by animateDpAsState(
                targetValue = if (startAnimation) women2Offsetx else (women2Offsetx),
                animationSpec = tween(durationMillis = 4000)
            )
            val women2OffsetyAnimated by animateDpAsState(
                targetValue = if (startAnimation) women2Offsety else (women2Offsety),
                animationSpec = tween(durationMillis = 4000)
            )

            // Animation for women3
            val women3OffsetxAnimated by animateDpAsState(
                targetValue = if (startAnimation) women3Offsetx else (women3Offsetx),
                animationSpec = tween(durationMillis = 4000)
            )
            val women3OffsetyAnimated by animateDpAsState(
                targetValue = if (startAnimation) women3Offsety else (women3Offsety),
                animationSpec = tween(durationMillis = 4000)
            )


            Image(
                painter = painterResource(id = R.drawable.women2),
                contentDescription = "women2",
                modifier = Modifier
                    .size(300.dp) //350
                    .offset(x = offsetX + women2OffsetxAnimated, y = women2OffsetyAnimated)
            )


            Image(
                painter = painterResource(id = R.drawable.women3),
                contentDescription = "women3",
                modifier = Modifier
                    .size(300.dp) //350
                    .offset(x = offsetX + women3OffsetxAnimated, y = women3OffsetyAnimated)
            )
        }

    }


}








