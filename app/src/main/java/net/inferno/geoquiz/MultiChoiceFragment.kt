package net.inferno.geoquiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_multi_choice.*

class MultiChoiceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_multi_choice, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val index = arguments.getInt("INDEX")
        val question = QuestionsData.questions[index]
        questionText.text = question.text
        val answer = QuestionsData.answers[index]
        for (i in 0 until question.options.size) {
            val checkBox = CheckBox(context)
            checkBox.layoutParams = LinearLayout.LayoutParams(-1, -2)
            checkBox.text = question.options[i]
            checkBox.isChecked = answer.contains(i.toString())
            checkBox.setOnCheckedChangeListener { _, _ -> QuestionsData.answers[index] = getAnswer() }
            answers.addView(checkBox)
        }
    }

    private fun getAnswer(): String {
        var string = ""
        for (i in 0 until answers.childCount) {
            val checkBox = answers.getChildAt(i) as CheckBox
            if (checkBox.isChecked) string += i
        }
        return string
    }
}
