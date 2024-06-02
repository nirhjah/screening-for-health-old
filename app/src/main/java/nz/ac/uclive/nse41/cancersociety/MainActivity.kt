package nz.ac.uclive.nse41.cancersociety

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import nz.ac.uclive.nse41.cancersociety.navigation.NavGraph
import nz.ac.uclive.nse41.cancersociety.ui.theme.CancerSocietyTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CancerSocietyTheme(content = {
                Scaffold(
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)

                }
            })


        }
    }
}


