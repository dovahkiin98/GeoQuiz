package net.inferno.geoquiz.data

import android.content.Context
import org.json.JSONArray
import java.util.*

object QuestionsData {

    val questions = mutableListOf<Question>()
    val answers = Array(10) { "" }

    fun init(context: Context) {
        val stream = context.assets.open("Questions.json")
        Scanner(stream).use {
            val json = StringBuilder()

            while (it.hasNext()) json.append(it.nextLine())

            val obj = JSONArray(json.toString())

            (0 until obj.length()).mapTo(questions) { Question(obj.getJSONObject(it)) }
        }
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