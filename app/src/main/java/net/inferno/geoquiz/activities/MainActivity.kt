package net.inferno.geoquiz.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.inferno.geoquiz.R
import net.inferno.geoquiz.data.QuestionsData
import net.inferno.geoquiz.fragments.*

class MainActivity : AppCompatActivity() {

    private val fragments = mutableListOf<Fragment>()
    private var currentFragment = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt("INDEX")
            checkButtons()
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeFragment())
                    .commit()
        }
        loadFragments()

        nextButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragments[++currentFragment])
                    .commit()

            checkButtons()
        }

        previousButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragments[--currentFragment])
                    .commit()

            checkButtons()
        }
    }

    private fun checkButtons() {
        nextButton.isEnabled = currentFragment != 10
        previousButton.isEnabled = currentFragment != 0

        if (currentFragment != 10) indicatorText.text = resources.getString(R.string.indicator, (currentFragment + 1), 10)
    }

    private fun loadFragments() {
        var i = 0
        QuestionsData.questions.forEach {
            val fragment = when (it.type) {
                0 -> TextFragment()
                1 -> MultiChoiceFragment()
                else -> SingleChoiceFragment()
            }
            val bundle = Bundle()
            bundle.putInt("INDEX", i++)
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        fragments.add(SubmitFragment())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("INDEX", currentFragment)
    }
}