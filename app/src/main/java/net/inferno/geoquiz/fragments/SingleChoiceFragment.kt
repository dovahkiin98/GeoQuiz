package net.inferno.geoquiz.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import kotlinx.android.synthetic.main.fragment_single_choice.*
import net.inferno.geoquiz.data.QuestionsData
import net.inferno.geoquiz.R

class SingleChoiceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_single_choice, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val index = arguments.getInt("INDEX")
        val question = QuestionsData.questions[index]
        questionText.text = question.text
        val answer = QuestionsData.answers[index]
        for (i in 0 until question.options.size) {
            val radioButton = RadioButton(context)
            radioButton.layoutParams = LinearLayout.LayoutParams(-1, -2)
            radioButton.text = question.options[i]
            answers.addView(radioButton)
            if (answer == i.toString()) radioButton.isChecked = true
        }
        answers.setOnCheckedChangeListener { _, _ -> QuestionsData.answers[index] = getAnswer() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("ANSWER", getAnswer())
    }

    private fun getAnswer(): String {
        var string = ""
        for (i in 0 until answers.childCount) {
            val radioButton = answers.getChildAt(i) as RadioButton
            if (radioButton.isChecked) string = i.toString()
        }
        return string
    }
}
