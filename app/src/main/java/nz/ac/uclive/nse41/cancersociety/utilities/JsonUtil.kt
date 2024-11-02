package nz.ac.uclive.nse41.cancersociety.utilities

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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

/**
 * This function gets a random quiz question based on the given cancer type and subsection topic name
 * This is used for the quiz questions between each topics of the full sequence/learn them all part of the app
 */
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

/**
 * This function gets a random subset of quiz questions based on cancer type - this is used for the 'test your knowledge on x cancer type' quiz for each cancer.
 */
fun getQuizQuestionsForCancerType(context: Context, fileName: String, cancerType: String, numberOfQuestions: Int = 5): List<QuizQuestion>? {
    val inputStream = context.assets.open(fileName)
    val reader = InputStreamReader(inputStream)
    val gson = Gson()

    val quizType = object : TypeToken<Map<String, List<QuizQuestion>>>() {}.type
    val quizData: Map<String, List<QuizQuestion>> = gson.fromJson(reader, quizType)

    val questions = quizData[cancerType] ?: return null

    return questions.shuffled().take(numberOfQuestions)
}


/**
 * Saves recorded time on screen to logs for internal purposes only
 */
fun saveLogToFile(context: Context, screenName: String, timeSpent: Long, cancerType: String) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault())
    val timestamp = dateFormat.format(Date())
    val log = "$timestamp $screenName $cancerType: $timeSpent ms\n"

    if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
        val file = File(ContextCompat.getExternalFilesDirs(context, null)[0], "screen_logs.txt")
        FileOutputStream(file, true).use {
            OutputStreamWriter(it).use { writer ->
                writer.append(log)
            }
        }
        Log.d("saveLogToFile", "Log saved: $log")
        Log.d("saveLogToFile", "File path: ${file.absolutePath}")
    } else {
        Log.e("saveLogToFile", "External storage is not writable")
    }
}