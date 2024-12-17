package com.example.tbcacademyhomework

class UsersDatabase {

    private val users: MutableMap<String, User> = mutableMapOf()
    private var deletedUsers = 0

    fun addUser(user: User): Response {
        if (userExists(user.email)) return Response.Error(R.string.user_already_exists)
        users[user.email] = user
        listener?.invoke(users.size, deletedUsers)
        return Response.Success()
    }

    fun removeUser(userEmail: String): Response {
        if (!userExists(userEmail)) return Response.Error(R.string.user_does_not_exist)
        users.remove(userEmail)
        listener?.invoke(users.size, ++deletedUsers)
        return Response.Success()


    }

    fun updateUser(user: User): Response {
        if (!userExists(user.email)) return Response.Error(R.string.user_does_not_exist)
        users[user.email] = user
        return Response.Success()
    }

    private fun userExists(userEmail: String): Boolean {
        return users[userEmail] != null
    }

    private var listener: ((activeUsers: Int, deletedUsers: Int) -> Unit)? = null

    fun onDataChangedListener(listener: (activeUsers: Int, deletedUsers: Int) -> Unit) {
        this.listener = listener
        this.listener?.invoke(users.size, deletedUsers)
    }


}