package com.tarren.personalquizapp.ui.Teacher.CSVImport

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarren.personalquizapp.R

class CsvImportFragment : Fragment() {

    companion object {
        fun newInstance() = CsvImportFragment()
    }

    private lateinit var viewModel: CsvImportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_csv_import, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CsvImportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}