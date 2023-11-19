package com.tarren.personalquizapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tarren.personalquizapp.R
import com.tarren.personalquizapp.databinding.FragmentRegisterBinding
import com.tarren.personalquizapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setupUIComponents()
        return binding.root
    }

    override fun setupUIComponents() {
        super.setupUIComponents()

        binding.run {

            btnRegister.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val confirmPassword = etConfirmPassword.text.toString()
                val role = when (rgRole.checkedRadioButtonId) {
                    R.id.rbStudent -> "Student"
                    R.id.rbTeacher -> "Teacher"
                    else -> ""
                }
                if (confirmPassword != password) {
                    // Show error message for password mismatch
                } else {
                    viewModel.register(name, email, password, confirmPassword, role)
                }
            }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.user.collect { user ->
                if (user != null) {
                    val action = when (user.role) {
                        "Student" -> RegisterFragmentDirections.actionRegisterToStudentDashboard()
                        "Teacher" -> RegisterFragmentDirections.actionRegisterToTeacherDashboard()
                        else -> throw IllegalStateException("Unknown role")
                    }
                    navController.navigate(action)
                }
            }
        }
    }
}
