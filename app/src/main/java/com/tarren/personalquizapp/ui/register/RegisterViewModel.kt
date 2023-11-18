package com.tarren.personalquizapp.ui.register

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.tarren.personalquizapp.core.service.AuthService
import com.tarren.personalquizapp.data.model.User
import com.tarren.personalquizapp.data.repo.UserRepo
import com.tarren.personalquizapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService,
    private val userRepo: UserRepo
) : BaseViewModel() {

    fun register(name: String, email: String, pass: String, confirmPass: String, role: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val error = validate(email, pass, confirmPass)
            if (error.isNotEmpty()) {
                _error.emit(error)
            } else {
                val res = safeApiCall { authService.register(email, pass) }
                if (res == null) {
                    _error.emit("Could not create user")
                } else {
                    userRepo.addUser(
                        res.uid,
                        User(name = name, email = res.email ?: "", role = role)
                    )
                    _success.emit("User created successfully")
                }
            }
        }
    }

    private fun validate(email: String, pass: String, confirmPass: String): String {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Please provide a valid email address"
            pass.length < 6 -> "Password length must be greater than 5"
            pass != confirmPass -> "Password and confirm password are not the same"
            else -> ""
        }
    }
}
