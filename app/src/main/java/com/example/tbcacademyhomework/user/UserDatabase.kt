package com.example.tbcacademyhomework.user

class UserDatabase {
    private val users = mutableListOf<User>()

    fun getUser(email: String): User? {
        return users.find { it.email == email }
    }

    fun addUser(user: User) {
        users.add(user)
    }

    fun getUserCount() = users.size


    fun userAlreadyExists(email: String) = getUser(email = email) != null

}