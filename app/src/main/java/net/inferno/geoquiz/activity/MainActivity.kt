package net.inferno.geoquiz.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData
import net.inferno.geoquiz.fragment.QuestionFragment
import net.inferno.geoquiz.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        next.setOnClickListener {
            if (mainViewModel.currentQuestion.value!! + 1 < 10) {
                mainViewModel.nextQuestion()
                getFragment()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Score")
                    .setMessage("You got ${QuestionsData.getScore()} questions out of ${QuestionsData.questions.size}")
                    .show()
            }
        }

        previous.setOnClickListener {
            mainViewModel.previousQuestion()

            getFragment()
        }

        mainViewModel.currentQuestion.observe(this) {
            previous.isEnabled = it > 0

            progress.progress = it + 1
            welcome.isVisible = it == -1
        }
    }

    private fun getFragment() = supportFragmentManager.beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(R.id.container, QuestionFragment.createInstance(mainViewModel.currentQuestion.value!!))
        .commit()
}