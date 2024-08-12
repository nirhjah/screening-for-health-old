package nz.ac.uclive.nse41.cancersociety


import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import nz.ac.uclive.nse41.cancersociety.ui.theme.Orange
import nz.ac.uclive.nse41.cancersociety.ui.theme.Purple40
import nz.ac.uclive.nse41.cancersociety.ui.theme.Purple80

@Composable
fun CustomProgressBar(currentScreenIndex: Int) {

    var progress by remember { mutableStateOf(0f) }

    // Map the current screen index to the progress value
    val targetProgress = when (currentScreenIndex) {
        0 -> 0.0f
        1 -> 0.25f
        2 -> 0.5f
        3 -> 0.75f
        4 -> 1.0f
        else -> 0.0f
    }

    // Animate the progress when the screen index changes
    LaunchedEffect(key1 = currentScreenIndex) {
        progress = targetProgress
    }

    val size by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(47.dp)
            .padding(start = 16.dp) // Add padding to make space for the Next button
    ) {
        // Text above the progress bar
        Row(
            modifier = Modifier
                .widthIn(min = 30.dp)
                .fillMaxWidth(size),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "${(progress * 100).toInt()}%")
        }
        // Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(17.dp)
        ) {
            // Background of the ProgressBar
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(9.dp))
                    .background(Purple80)
            )
            // Progress of the ProgressBar
            Box(
                modifier = Modifier
                    .fillMaxWidth(size)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(Purple40)
                    .animateContentSize()
            )
        }
    }
}
