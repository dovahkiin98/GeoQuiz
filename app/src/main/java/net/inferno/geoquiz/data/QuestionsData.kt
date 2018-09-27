package net.inferno.geoquiz.data

import android.content.Context
import net.inferno.geoquiz.R
import org.json.JSONArray
import java.util.*

object QuestionsData {

    val questions = mutableListOf<Question>()
    lateinit var answers: Array<String>

    fun init(context: Context) {
        Scanner(context.resources.openRawResource(R.raw.questions)).use {
            it.useDelimiter("\\A")

            JSONArray(it.next()).apply {
                for (index in 0 until length()) questions += Question(getJSONObject(index))
            }
        }
        answers = Array(questions.size) { "" }
        questions.shuffle()
    }

    fun getScore(): Int {
        var score = 0
        for (i in 0 until questions.size) {
            val answer = questions[i].answer
            val current = answers[i]
            if (answer.compareTo(current, true) == 0) {
                score++
            }
        }
        return score
    }
}