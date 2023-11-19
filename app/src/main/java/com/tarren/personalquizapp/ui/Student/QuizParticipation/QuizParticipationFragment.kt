package com.tarren.personalquizapp.ui.Student.QuizParticipation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarren.personalquizapp.R

class QuizParticipationFragment : Fragment() {

    companion object {
        fun newInstance() = QuizParticipationFragment()
    }

    private lateinit var viewModel: QuizParticipationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_participation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizParticipationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}