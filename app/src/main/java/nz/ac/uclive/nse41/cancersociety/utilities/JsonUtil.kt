package nz.ac.uclive.nse41.cancersociety.utilities

import android.content.Context
import com.google.gson.Gson
import java.io.IOException


/**
 * Function to read CancerInfo from the Json
 */
fun getCancerInfoFromJson(context: Context, fileName: String): CancerInfo? {

    return try {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        Gson().fromJson(jsonString, CancerInfo::class.java)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

data class CancerInfo(
    val cancers: List<Cancer>
)

data class Cancer(
    val cancer: String,
    val subsections: List<Subsection>
)

data class Subsection(
    val subsection: String,
    val info: List<String>
)