package net.inferno.geoquiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import net.inferno.geoquiz.data.Question
import net.inferno.geoquiz.data.QuestionsData

open class QuestionFragment : Fragment() {

    protected open val layoutRes: Int = 0
    protected lateinit var question: Question
    protected var index = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutRes, container, false)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        index = arguments!!.getInt("INDEX")
        question = QuestionsData.questions[index]
    }

    companion object {
        fun createInstance(index: Int) = when (QuestionsData.questions[index].type) {
            0 -> TextFragment()
            1 -> MultiChoiceFragment()
            2 -> SingleChoiceFragment()
            else -> QuestionFragment()
        }.apply { arguments = bundleOf("INDEX" to index) }
    }
}