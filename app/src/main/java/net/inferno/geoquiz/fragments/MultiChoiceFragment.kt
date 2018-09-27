package net.inferno.geoquiz.fragments

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.view.forEachIndexed
import androidx.core.view.plusAssign
import kotlinx.android.synthetic.main.fragment_multi_choice.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData

class MultiChoiceFragment : QuestionFragment() {

    override val layoutRes = R.layout.fragment_multi_choice

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionText.text = question.text
        question.options.forEachIndexed { i, it ->
            answers += CheckBox(context).apply {
                layoutParams = LinearLayout.LayoutParams(-1, -2)
                text = it
                isChecked = QuestionsData.answers[index].contains("$i")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        var string = ""
        answers.forEachIndexed { index, view -> if ((view as CheckBox).isChecked) string += "$index" }
        QuestionsData.answers[index] = string
    }
}
