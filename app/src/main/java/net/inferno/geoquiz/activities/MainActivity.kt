package net.inferno.geoquiz.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData
import net.inferno.geoquiz.fragments.QuestionFragment

class MainActivity : AppCompatActivity() {

    private var currentQuestion = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) currentQuestion = savedInstanceState.getInt("INDEX")

        next.setOnClickListener {
            currentQuestion++

            if (currentQuestion < 10) getFragment()
            else {
                AlertDialog.Builder(this)
                    .setTitle("Score")
                    .setMessage("You scored ${QuestionsData.getScore()} out of ${QuestionsData.questions.size}")
                    .show()
                currentQuestion--
            }

            checkButtons()
        }

        previous.setOnClickListener {
            currentQuestion--

            getFragment()

            checkButtons()
        }

        checkButtons()
    }

    private fun checkButtons() {
        previous.isEnabled = currentQuestion > 0

        progress.progress = currentQuestion + 1
        welcome.isVisible = currentQuestion == -1
    }

    private fun getFragment() = supportFragmentManager.beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(R.id.container, QuestionFragment.createInstance(currentQuestion))
        .commit()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("INDEX", currentQuestion)
    }
}