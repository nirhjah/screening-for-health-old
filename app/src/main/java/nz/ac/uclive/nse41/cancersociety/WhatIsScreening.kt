import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import nz.ac.uclive.nse41.cancersociety.CustomButton
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme

@Composable
fun WhatIsScreening(navController: NavController) {
    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color(red = 0, green = 0, blue = 0)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedImageSlide()




                Box(modifier = Modifier.fillMaxSize()) {
                    CustomButton(
                        text = "Next",
                        route = Screens.Statistics.route,
                        navController = navController,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedImageSlide() {
    var startAnimation by remember { mutableStateOf(false) }
    var textToShow by remember { mutableStateOf(R.string.even_if_you_feel_well) }
    var textAlpha by remember { mutableStateOf(1f) }


    var women2Offset by remember { mutableStateOf(110.dp) }
    var women3Offset by remember { mutableStateOf(220.dp) }

    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else (-550).dp,
        animationSpec = tween(durationMillis = 4000)
    )

    val alpha by animateFloatAsState(
        targetValue = textAlpha,
        animationSpec = tween(durationMillis = 1000)
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
        women2Offset += 200.dp
        women3Offset += 200.dp
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "What is Screening?",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = R.drawable.hospital),
                contentDescription = "hospital",
                modifier = Modifier.size(350.dp)
            )


            Text(
                text = stringResource(textToShow),
                fontSize = 30.sp,
                modifier = Modifier.graphicsLayer(alpha = alpha),
                fontWeight = FontWeight.Bold
            )



        }



        Image(
            painter = painterResource(id = R.drawable.women1),
            contentDescription = "women1",
            modifier = Modifier
                .size(350.dp)
                .offset(x = offsetX)
        )

        // Animation for women2
        val women2OffsetAnimated by animateDpAsState(
            targetValue = if (startAnimation) women2Offset else (women2Offset),
            animationSpec = tween(durationMillis = 4000)
        )

        Image(
            painter = painterResource(id = R.drawable.women1),
            contentDescription = "women2",
            modifier = Modifier
                .size(350.dp)
                .offset(x = offsetX + women2OffsetAnimated)
        )

        // Animation for women3
        val women3OffsetAnimated by animateDpAsState(
            targetValue = if (startAnimation) women3Offset else (women3Offset),
            animationSpec = tween(durationMillis = 4000)
        )

        Image(
            painter = painterResource(id = R.drawable.women1),
            contentDescription = "women3",
            modifier = Modifier
                .size(350.dp)
                .offset(x = offsetX + women3OffsetAnimated)
        )
    }
}
