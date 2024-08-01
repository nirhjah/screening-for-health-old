package nz.ac.uclive.nse41.cancersociety.utilities

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import kotlin.random.Random

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


data class QuizQuestion(
    val question: String,
    val subsection: String,
    val answers: List<String>,
    val correctAnswer: String,
    val response: String,
)

fun getQuizQuestionForCancerTypeAndSubsection(context: Context, fileName: String, cancerType: String, subSection: String): QuizQuestion? {
    val inputStream = context.assets.open(fileName)
    val reader = InputStreamReader(inputStream)
    val gson = Gson()

    val quizType = object : TypeToken<Map<String, List<QuizQuestion>>>() {}.type
    val quizData: Map<String, List<QuizQuestion>> = gson.fromJson(reader, quizType)

    val questions = quizData[cancerType]?.filter { it.subsection == subSection }

    if (questions.isNullOrEmpty()) {
        return null
    }

    return questions[Random.nextInt(questions.size)]
}

