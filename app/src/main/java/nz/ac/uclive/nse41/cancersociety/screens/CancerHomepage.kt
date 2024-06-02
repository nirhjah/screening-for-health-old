package nz.ac.uclive.nse41.cancersociety.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    Text(
                        text = cancerType,
                        fontSize = 84.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 66.dp, top = 32.dp)
                            .align(Alignment.Start)
                            .semantics { testTag = "cancerHomepageTitle" } ,
                        textAlign = TextAlign.Left,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.95f)
                            .background(Color.White, shape = RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = Orange,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .size(width = 680.dp, height = 80.dp)
                                    .clickable {
                                        //TODO go to quiz
                                        Toast.makeText(context, "Quiz feature coming soon", Toast.LENGTH_SHORT).show()
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
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                                    colors = CardDefaults.cardColors(containerColor = Orange,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        .clickable {
                                            Log.d("MyActivity", cancerType + "stats")

                                            navController.navigate("${Screens.Statistics.route}/$fullSequence/$cancerType") }

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
                                    colors = CardDefaults.cardColors(containerColor = Orange,
                                        contentColor = Color.Black),
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 200.dp)
                                        .clickable {  navController.navigate("${Screens.WhereToGetScreened.route}/$fullSequence/$cancerType") }

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
                                        .clickable {  navController.navigate("${Screens.BarriersToGettingScreened.route}/$fullSequence/$cancerType") }

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

                            Text(
                                text = "Useful links",
                                fontSize = 26.sp,
                                modifier = Modifier.padding(26.dp),
                                textAlign = TextAlign.Left,
                                color = Color.Black
                            )

                            Text(
                                text = "Useful links",
                                fontSize = 26.sp,
                                modifier = Modifier.padding(26.dp),
                                textAlign = TextAlign.Left,
                                color = Color.Black
                            )

                            Text(
                                text = "Useful links",
                                fontSize = 26.sp,
                                modifier = Modifier.padding(26.dp),
                                textAlign = TextAlign.Left,
                                color = Color.Black
                            )


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
        "Cervical Cancer" -> Bluey
        else -> Orange // if for some reason doesn't exist
    }
}