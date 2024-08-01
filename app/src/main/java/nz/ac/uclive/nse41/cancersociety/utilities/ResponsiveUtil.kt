package nz.ac.uclive.nse41.cancersociety.utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Sets font size of text (for headers) depending on screen size/orientation
 */
@Composable
fun responsiveFontSize(): TextUnit {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    return when {
        screenWidthDp < 412 -> 40.sp
        screenWidthDp < 801 -> 60.sp
        else -> 59.sp //84 used to be
    }
}


/**
 * Sets size of the hospital image displayed on the what is screening page, depending on screen size/orientation
 */
@Composable
fun responsiveHospitalImage(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    return when {
        screenWidthDp < 412 -> 250.dp
        else -> 350.dp
    }
}