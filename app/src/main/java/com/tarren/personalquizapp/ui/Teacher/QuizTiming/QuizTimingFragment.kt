package com.tarren.personalquizapp.ui.Teacher.QuizTiming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarren.personalquizapp.R

class QuizTimingFragment : Fragment() {

    companion object {
        fun newInstance() = QuizTimingFragment()
    }

    private lateinit var viewModel: QuizTimingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_timing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizTimingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}