package nz.ac.uclive.nse41.cancersociety.utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun responsiveFontSize(): TextUnit {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    return when {
        screenWidthDp < 412 -> 40.sp
        screenWidthDp < 801 -> 60.sp
        else -> 84.sp
    }
}


@Composable
fun responsiveHospitalImage(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    return when {
        screenWidthDp < 412 -> 250.dp
        else -> 350.dp
    }
}