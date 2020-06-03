package net.inferno.geoquiz.fragment

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.plusAssign
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.fragment_single_choice.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData

class SingleChoiceFragment : QuestionFragment(R.layout.fragment_single_choice) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionText.text = question.text

        question.options.forEachIndexed { i, it ->
            answers += RadioButton(context).apply {
                layoutParams = LinearLayout.LayoutParams(-1, -2)
                setPadding(TypedValue.applyDimension(
                    TypedValue.DATA_NULL_EMPTY,
                    8f,
                    resources.displayMetrics
                ).toInt())
                text = it
            }
            (answers[i] as RadioButton).isChecked = QuestionsData.answers[index] == "$i"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        var string = ""
        answers.forEachIndexed { index, view -> if ((view as RadioButton).isChecked) string = "$index" }
        QuestionsData.answers[index] = string
    }
}