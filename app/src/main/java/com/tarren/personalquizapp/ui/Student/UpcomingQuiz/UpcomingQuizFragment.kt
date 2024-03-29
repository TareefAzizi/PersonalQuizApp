package com.tarren.personalquizapp.ui.Student.UpcomingQuiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarren.personalquizapp.R

class UpcomingQuizFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingQuizFragment()
    }

    private lateinit var viewModel: UpcomingQuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming_quiz, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpcomingQuizViewModel::class.java)
        // TODO: Use the ViewModel
    }

}