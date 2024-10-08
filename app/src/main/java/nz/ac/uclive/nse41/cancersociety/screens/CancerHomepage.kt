package nz.ac.uclive.nse41.cancersociety.screens

import BackButton
import HomepageBackButton
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.R
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.Bluey
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.ui.theme.Green
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.ui.theme.Pink

@Composable
fun CancerHomepageScreen(navController: NavController, cancerType: String) {
    Log.d("MyActivity", cancerType)
    val containerColor = getCancerTypeColor(cancerType)
    val context = LocalContext.current
    var fullSequence = false

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    Log.d("This is screen",  " this is screenwdith " + screenWidthDp)
    val fontSize = when {
        screenWidthDp < 412 -> 54.sp
        else -> 84.sp
    }
    val start = when {
        screenWidthDp < 412 -> 10.dp
        else -> 66.dp
    }
    val topStart = when {
        screenWidthDp < 412 -> 70.dp
        else -> 100.dp
    }
    val topEnd = when {
        screenWidthDp < 412 -> 70.dp
        else -> 100.dp
    }
    val topPadding = when {
        screenWidthDp < 412 -> 26.dp
        else -> 0.dp
    }


    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("CancerHomepage", "Time spent: $timeSpent ms")

            saveLogToFile(context, "CancerHomepage", timeSpent, cancerType.toString())
        }
    }


    CancerSocietyTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {


                        HomepageBackButton(navController)



                    Row(
                        modifier = Modifier
                            .padding(start = start, top = 0.dp)
                            .align(Alignment.Start)
                            .semantics { testTag = "cancerHomepageTitle" }
                    ) {
                        Text(
                            text = cancerType,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )


                        // Image of women1
                        Image(
                            painter = painterResource(id = R.drawable.women1),
                            contentDescription = null,
                            modifier = Modifier
                                .size(168.dp)
                                .align(Alignment.CenterVertically)
                        )


                        val imageRes2 = if (cancerType == "Bowel Cancer") {
                            R.drawable.men2
                        } else {
                            R.drawable.women2
                        }


                        // Image of women2
                        Image(
                            painter = painterResource(id = imageRes2),
                            contentDescription = null,
                            modifier = Modifier
                                .size(168.dp)
                                .align(Alignment.CenterVertically)
                        )


                        val imageRes3 = if (cancerType == "Bowel Cancer") {
                            R.drawable.men3
                        } else {
                            R.drawable.women3
                        }

                        Image(
                            painter = painterResource(id = imageRes3),
                            contentDescription = null,
                            modifier = Modifier
                                .size(168.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.95f)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topStart = topStart, topEnd = topEnd) //100, 100
                            )
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth().padding(top = topPadding)

                        ) {
                        //todo add big quiz
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = Bluey,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .size(width = 680.dp, height = 80.dp)
                                    .clickable {
                                        //TODO go to quiz
                                        Toast
                                            .makeText(
                                                context,
                                                "Quiz feature coming soon",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.quiz_icon),
                                        contentDescription = "Quiz Icon",
                                        tint = Color.Black,
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "Test your knowledge of $cancerType in NZ",
                                        fontSize = 26.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }



                            Spacer(modifier = Modifier.height(20.dp))

                            Divider(thickness = 1.dp, color = Color.LightGray)
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "Learn more about...",
                                fontSize = 26.sp,
                                modifier = Modifier
                                    .padding(start = 66.dp)
                                    .align(Alignment.Start),
                                textAlign = TextAlign.Left,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.horizontalScroll(rememberScrollState())
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = containerColor,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        .clickable { navController.navigate("${Screens.WhatIsScreening.route}/$fullSequence/$cancerType") }


                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.quiz_icon),
                                                contentDescription = "What Icon",
                                                tint = Color.Black,
                                                modifier = Modifier.size(48.dp)
                                            )

                                            Text(
                                                text = "What is screening?",
                                                fontWeight = FontWeight.ExtraBold,
                                                modifier = Modifier.padding(16.dp),
                                                textAlign = TextAlign.Center,
                                            )

                                        }
                                    }
                                }

                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Bluey,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        /*.clickable {
                                            Log.d("MyActivity", cancerType + "stats")

                                            navController.navigate("${Screens.Statistics.route}/$fullSequence/$cancerType")
                                        }*/
                                        .clickable {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Statistics page coming soon",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }

                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.stats_icon),
                                                contentDescription = "Quiz Icon",
                                                tint = Color.Black,
                                                modifier = Modifier.size(48.dp)
                                            )

                                            Text(
                                                text = "Statistics",
                                                fontWeight = FontWeight.ExtraBold,
                                                modifier = Modifier.padding(16.dp),
                                                textAlign = TextAlign.Center,
                                            )

                                        }
                                    }
                                }

                                Card(
                                    colors = CardDefaults.cardColors(containerColor = containerColor,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        .clickable { navController.navigate("${Screens.WhoCanGetScreened.route}/$fullSequence/$cancerType") }

                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.who_icon),
                                                contentDescription = "Who Icon",
                                                tint = Color.Black,
                                                modifier = Modifier.size(48.dp)
                                            )

                                            Text(
                                                text = "Who can get screened?",
                                                fontWeight = FontWeight.ExtraBold,
                                                modifier = Modifier.padding(16.dp),
                                                textAlign = TextAlign.Center,
                                            )

                                        }
                                    }
                                }

                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Bluey,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        .clickable { navController.navigate("${Screens.WhereToGetScreened.route}/$fullSequence/$cancerType") }

                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.where_icon),
                                                contentDescription = "Where Icon",
                                                tint = Color.Black,
                                                modifier = Modifier.size(48.dp)
                                            )

                                            Text(
                                                text = "Where to get screened?",
                                                fontWeight = FontWeight.ExtraBold,
                                                modifier = Modifier.padding(16.dp),
                                                textAlign = TextAlign.Center,
                                            )

                                        }
                                    }
                                }

                                Card(
                                    colors = CardDefaults.cardColors(containerColor = containerColor,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        .clickable { navController.navigate("${Screens.BarriersToGettingScreened.route}/$fullSequence/$cancerType") }

                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.barriers_icon),
                                                contentDescription = "Barrier Icon",
                                                tint = Color.Black,
                                                modifier = Modifier.size(48.dp)
                                            )

                                            Text(
                                                text = "Barriers to getting screened",
                                                fontWeight = FontWeight.ExtraBold,
                                                modifier = Modifier.padding(16.dp),
                                                textAlign = TextAlign.Center,
                                            )

                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = containerColor,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .size(width = 310.dp, height = 80.dp)
                                    .clickable {
                                        fullSequence = true
                                        navController.navigate("${Screens.WhatIsScreening.route}/$fullSequence/$cancerType")


                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.quiz_icon),
                                        contentDescription = "Quiz Icon",
                                        tint = Color.Black,
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "Or learn them all!",
                                        fontSize = 26.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Divider(thickness = 1.dp, color = Color.LightGray)
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "Useful links",
                                fontSize = 26.sp,
                                modifier = Modifier
                                    .padding(start = 66.dp)
                                    .align(Alignment.Start),
                                textAlign = TextAlign.Left,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(20.dp))


                            val link2 = when (cancerType) {
                                "Bowel Cancer" -> "https://bowelcancernz.org.nz/about-bowel-cancer/what-is-bowel-cancer/symptoms-statistics/"
                                "Breast Cancer" -> "https://www.breastcancerfoundation.org.nz/breast-awareness/mammograms"
                                else -> "https://www.tewhatuora.govt.nz/health-services-and-programmes/ncsp-hpv-screening/"
                            }

                            val text2 = when (cancerType) {
                                "Bowel Cancer" -> "Bowel Cancer NZ"
                                "Breast Cancer" -> "Breast Cancer Foundation"
                                else -> "Te Whatu Ora"
                            }


                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link2))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),

                                ) {
                                Text(text2)
                            }

                            val link3 = when (cancerType) {
                                "Bowel Cancer" -> "https://www.cancer.org.nz/cancer/types-of-cancer/bowel-cancer/"
                                "Breast Cancer" -> "https://www.cancer.org.nz/cancer/types-of-cancer/breast-cancer/"
                                else -> "https://www.cancer.org.nz/cancer/types-of-cancer/cervical-cancer/"
                            }

                            val text3 = when (cancerType) {
                                "Bowel Cancer" -> "Cancer Society"
                                "Breast Cancer" -> "Cancer Society"
                                else -> "Cancer Society"
                            }


                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link3))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                colors = ButtonDefaults.buttonColors(containerColor = Bluey, contentColor = Color.Black),

                                ) {
                                Text(text3)
                            }



                        }

                    }

                }
            }
        }
    }
}

fun getCancerTypeColor(cancerType: String): Color {
    return when (cancerType) {
        "Breast Cancer" -> Pink
        "Bowel Cancer" -> Green
        "Cervical Cancer" -> Orange
        else -> Bluey // if for some reason doesn't exist
    }
}