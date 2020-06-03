package net.inferno.geoquiz.fragment

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.view.forEachIndexed
import androidx.core.view.plusAssign
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.fragment_multi_choice.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData

class MultiChoiceFragment : QuestionFragment(R.layout.fragment_multi_choice) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionText.text = question.text
        question.options.forEachIndexed { i, it ->
            answers += CheckBox(context).apply {
                layoutParams = LinearLayout.LayoutParams(-1, -2)
                text = it
                setPadding(TypedValue.applyDimension(
                    TypedValue.DATA_NULL_EMPTY,
                    8f,
                    resources.displayMetrics
                ).toInt())
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
