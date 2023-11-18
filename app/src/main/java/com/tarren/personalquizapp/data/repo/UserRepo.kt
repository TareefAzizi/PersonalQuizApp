package com.tarren.personalquizapp.data.repo

import com.tarren.personalquizapp.data.model.User

interface UserRepo {
    suspend fun addUser(id: String, user: User)
    suspend fun getUser(id: String): User?
    suspend fun updateUser(id: String, user: User)
}