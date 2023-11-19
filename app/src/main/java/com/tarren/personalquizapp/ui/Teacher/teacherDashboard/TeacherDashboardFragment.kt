package com.tarren.personalquizapp.ui.Teacher.teacherDashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarren.personalquizapp.R

class TeacherDashboardFragment : Fragment() {
    private lateinit var viewModel: TeacherDashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_dashboard, container, false)
    }

}