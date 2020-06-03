package net.inferno.geoquiz.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import net.inferno.geoquiz.data.QuestionsData
import net.inferno.geoquiz.model.Question
import java.security.InvalidParameterException

open class QuestionFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected lateinit var question: Question
    protected var index = 0

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        index = requireArguments().getInt("INDEX")
        question = QuestionsData.questions[index]
    }

    companion object {
        fun createInstance(index: Int) = when (QuestionsData.questions[index].type) {
            0 -> TextFragment()
            1 -> MultiChoiceFragment()
            2 -> SingleChoiceFragment()
            else -> throw InvalidParameterException("Required type is invalid")
        }.apply { arguments = bundleOf("INDEX" to index) }
    }
}