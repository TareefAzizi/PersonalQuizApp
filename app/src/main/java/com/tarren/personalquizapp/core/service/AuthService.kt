package com.tarren.personalquizapp.core.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthService(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    // Registers a new user with email and password, and returns the FirebaseUser.
    suspend fun register(email: String, pass: String): FirebaseUser? {
        val task = auth.createUserWithEmailAndPassword(email, pass).await()
        return task.user
    }

    // Logs in a user with email and password, and returns the FirebaseUser.
    suspend fun login(email: String, pass: String): FirebaseUser? {
        val task = auth.signInWithEmailAndPassword(email, pass).await()
        return task.user
    }

    // Returns the currently logged-in FirebaseUser, if any.
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    // Logs out the current user.
    fun logout() {
        auth.signOut()
    }
}