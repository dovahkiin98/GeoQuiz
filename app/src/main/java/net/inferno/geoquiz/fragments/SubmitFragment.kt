package net.inferno.geoquiz.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_submit_fragment.*
import net.inferno.geoquiz.data.QuestionsData
import net.inferno.geoquiz.R

class SubmitFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_submit_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            Toast.makeText(context, "You scored ${QuestionsData.getScore()} out of 10", Toast.LENGTH_LONG).show()
        }
    }
}
