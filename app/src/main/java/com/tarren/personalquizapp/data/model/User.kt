package com.tarren.personalquizapp.data.model

data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val role: String
) {
    // Function to convert User object to a HashMap
    fun toHashMap(): HashMap<String, String?> {
        return hashMapOf(
            "name" to name,
            "email" to email,
            "role" to role // Include the "role" in the HashMap
        )
    }

    companion object {
        // Function to create a User object from a HashMap
        fun fromHashMap(hash: Map<String, Any>): User {
            return User(
                id = hash["id"].toString(),
                name = hash["name"].toString(),
                email = hash["email"].toString(),
                role = hash["role"].toString() // Extract the "role" from the HashMap
            )
        }
    }
}
