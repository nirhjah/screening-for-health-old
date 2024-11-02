package nz.ac.uclive.nse41.cancersociety.screens

import BackButton
import CustomButton
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import nz.ac.uclive.nse41.cancersociety.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import nz.ac.uclive.nse41.cancersociety.CustomProgressBar
import nz.ac.uclive.nse41.cancersociety.navigation.Screens
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme
import nz.ac.uclive.nse41.cancersociety.utilities.Subsection
import nz.ac.uclive.nse41.cancersociety.utilities.getCancerInfoFromJson
import nz.ac.uclive.nse41.cancersociety.utilities.responsiveFontSize
import nz.ac.uclive.nse41.cancersociety.utilities.saveLogToFile
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WhoCanGetScreenedScreen(navController: NavController, fullSequence: Boolean, cancerType: String?) {
    val context = LocalContext.current
    val cancerInfo = getCancerInfoFromJson(context, "CancerInfo.json")
    val selectedCancer = cancerInfo?.cancers?.find { it.cancer == cancerType }
    val whoCanGetScreenedSubSection = selectedCancer?.subsections?.find { it.subsection == "Who can get screened" }

    //Times how long user spent on the screen - for internal purposes only

    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

    DisposableEffect(Unit) {
        onDispose {
            val timeSpent = System.currentTimeMillis() - startTime
            Log.d("WhoCanGetScreenedScreen", "Time spent: $timeSpent ms")

            saveLogToFile(context, "WhoCanGetScreenedScreen", timeSpent, cancerType.toString())
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
                    CustomProgressBar(
                        currentScreenIndex = 1,
                        modifier = Modifier.align(Alignment.BottomCenter).zIndex(1f)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp),
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Who can get screened?",
                        fontSize = responsiveFontSize(),
                        fontWeight = FontWeight.Bold
                    )

                    if (whoCanGetScreenedSubSection != null) {
                        PagerStepThree(cancerType = cancerType.toString(), whoCanGetScreenedSubSection = whoCanGetScreenedSubSection)
                    }


                }




                    Box(modifier = Modifier.fillMaxSize()) {
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
                                    route = "${Screens.Quiz.route}/WhoCanGetScreened/WhereToGetScreened",
                                    navController = navController,
                                    fullSequence = fullSequence,
                                    cancerType = cancerType,
                                    enabled = true,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }}
                    }
                    BackButton(navController)

            }
        }
    }
}

/**
 * Displays the image of the person with the age they can get screened - used for the pager.
 */
@Composable
fun EligibilityItem(imageRes: Int, text: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(1200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}


/**
 * This pager is to display the 3 different slides for the who can get screened screen
 * The first slide of the pagers shows the minimum age to get screened
 * The second slide shows the maximum age
 * The third slide shows a generic message the same for all cancers.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerStepThree(cancerType: String, whoCanGetScreenedSubSection: Subsection) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val pageCount = 3
        val pagerState = rememberPagerState(
            pageCount = { pageCount },
        )
        val indicatorScrollState = rememberLazyListState()

        LaunchedEffect(key1 = pagerState.currentPage) {
            val currentPage = pagerState.currentPage
            val size = indicatorScrollState.layoutInfo.visibleItemsInfo.size
            val lastVisibleIndex =
                indicatorScrollState.layoutInfo.visibleItemsInfo.last().index
            val firstVisibleItemIndex = indicatorScrollState.firstVisibleItemIndex

            if (currentPage > lastVisibleIndex - 1) {
                indicatorScrollState.animateScrollToItem(currentPage - size + 2)
            } else if (currentPage <= firstVisibleItemIndex + 1) {
                indicatorScrollState.animateScrollToItem(kotlin.math.max(currentPage - 1, 0))
            }
        }


        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { pageIndex ->

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                //To use a different mix of men/women images for bowel cancer vs other cancer types
                val images = if (cancerType == "Bowel Cancer") {
                    listOf(
                        R.drawable.men1,
                        R.drawable.women2,
                        R.drawable.men3
                    )
                } else {
                    listOf(
                        R.drawable.women1,
                        R.drawable.women2,
                        R.drawable.women3
                    )
                }

                if (pageIndex != pagerState.pageCount - 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(text = "Swipe!", fontSize = 25.sp)
                    }
                }

                val info = whoCanGetScreenedSubSection.info.getOrNull(pageIndex)
                val imageRes = images.getOrNull(pageIndex) ?: R.drawable.women1

                if (info != null) {
                    //displays the age with the person image
                    EligibilityItem(imageRes = imageRes, text = info)
                }
            }
        }

        LazyRow(
            state = indicatorScrollState,
            modifier = Modifier
                .height(50.dp)
                .width(((6 + 16) * 2 + 3 * (10 + 16)).dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                item(key = "item$iteration") {
                    val currentPage = pagerState.currentPage
                    val firstVisibleIndex by remember { derivedStateOf { indicatorScrollState.firstVisibleItemIndex } }
                    val lastVisibleIndex = indicatorScrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    val size by animateDpAsState(
                        targetValue = if (iteration == currentPage) {
                            10.dp
                        } else if (iteration in firstVisibleIndex + 1..lastVisibleIndex - 1) {
                            10.dp
                        } else {
                            6.dp
                        }
                    )
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(color, CircleShape)
                            .size(size)
                    )
                }
            }
        }
    }
}
