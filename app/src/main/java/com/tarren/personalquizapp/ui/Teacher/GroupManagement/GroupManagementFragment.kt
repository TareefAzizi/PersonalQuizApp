package com.tarren.personalquizapp.ui.Teacher.GroupManagement

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarren.personalquizapp.R

class GroupManagementFragment : Fragment() {

    companion object {
        fun newInstance() = GroupManagementFragment()
    }

    private lateinit var viewModel: GroupManagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_management, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GroupManagementViewModel::class.java)
        // TODO: Use the ViewModel
    }

}