package com.example.tbcacademyhomework.user

class UserDatabase {
    private val users = mutableListOf<User>()

    private var countListener: ((Int) -> Unit)? = null

    fun getUser(email: String): User? {
        return users.find { it.email == email.lowercase().trim() }
    }


    fun addUser(user: User) {
        users.add(user.copy(email = user.email.lowercase().trim()))
        countListener?.invoke(userCount)
    }

    private val userCount
        get() = users.size


    fun userAlreadyExists(email: String) = getUser(email = email) != null


    fun onUserCountChangedListener(listener: (Int) -> Unit) {
        countListener = listener
        countListener?.invoke(userCount)
    }

}