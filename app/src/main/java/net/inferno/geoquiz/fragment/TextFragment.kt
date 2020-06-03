package net.inferno.geoquiz.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_text.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData

class TextFragment : QuestionFragment(R.layout.fragment_text) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionText.text = question.text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        QuestionsData.answers[index] = answer.toString()
    }
}